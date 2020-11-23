package com.exercise1.initdb.graffitiremoval

import com.exercise1.domain.database.graffitiremoval.GraffitiRemoval
import com.exercise1.domain.database.graffitiremoval.GraffitiRemovalRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class GraffitiRemovalManipulator(
  private val graffitiRemovalRepository: GraffitiRemovalRepository,
  private val dataToGraffitiRemovalService: DataToGraffitiRemovalService
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(GraffitiRemovalManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<GraffitiRemoval>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    graffitiRemovalRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<GraffitiRemoval>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val graffitiRemoval = dataToGraffitiRemovalService.convertAndSave(line)
      list.add(graffitiRemoval)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToGraffitiRemovalService.convertAndSaveAsDump(line)
    }
  }
}
