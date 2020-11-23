package com.exercise1.initdb.garbagecarts

import com.exercise1.domain.database.garbagecarts.GarbageCarts
import com.exercise1.domain.database.garbagecarts.GarbageCartsRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class GarbageCartsManipulator(
  private val garbageCartsRepository: GarbageCartsRepository,
  private val dataToGarbageCartsService: DataToGarbageCartsService
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(GarbageCartsManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<GarbageCarts>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    garbageCartsRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<GarbageCarts>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val garbageCarts = dataToGarbageCartsService.convertAndSave(line)
      list.add(garbageCarts)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToGarbageCartsService.convertAndSaveAsDump(line)
    }
  }
}
