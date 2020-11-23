package com.exercise1.initdb.treetrims

import com.exercise1.domain.database.treetrims.TreeTrims
import com.exercise1.domain.database.treetrims.TreeTrimsRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class TreeTrimsManipulator(
  private val treeTrimsRepository: TreeTrimsRepository,
  private val dataToTreeTrimsService: DataToTreeTrimsService
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(TreeTrimsManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<TreeTrims>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    treeTrimsRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<TreeTrims>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val garbageCarts = dataToTreeTrimsService.convertAndSave(line)
      list.add(garbageCarts)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToTreeTrimsService.convertAndSaveAsDump(line)
    }
  }
}
