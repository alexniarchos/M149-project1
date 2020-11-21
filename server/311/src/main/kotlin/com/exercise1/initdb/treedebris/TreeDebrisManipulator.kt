package com.exercise1.initdb.treedebris

import com.exercise1.domain.database.treedebris.TreeDebris
import com.exercise1.domain.database.treedebris.TreeDebrisRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class TreeDebrisManipulator(
  private val treeDebrisRepository: TreeDebrisRepository,
  private val dataToTreeDebrisService: DataToTreeDebrisService
): DataManipulator {
  private val logger = LoggerFactory.getLogger(TreeDebrisManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<TreeDebris>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    treeDebrisRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<TreeDebris>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val garbageCarts = dataToTreeDebrisService.convertAndSave(line)
      list.add(garbageCarts)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToTreeDebrisService.convertAndSaveAsDump(line)
    }
  }
}
