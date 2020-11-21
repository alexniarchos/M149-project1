package com.exercise1.initdb.streetlightsoneout

import com.exercise1.domain.database.streetlightsoneout.StreetLightsOneOut
import com.exercise1.domain.database.streetlightsoneout.StreetLightsOneOutRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class StreetLightsOneOutManipulator(
  private val streetLightsOneOutRepository: StreetLightsOneOutRepository,
  private val dataToStreetLightsOneOutManipulator: DataToStreetLightsOneOutManipulator
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(StreetLightsOneOutManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<StreetLightsOneOut>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    streetLightsOneOutRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<StreetLightsOneOut>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val streetLightsOneOut = dataToStreetLightsOneOutManipulator.convertAndSave(line)
      list.add(streetLightsOneOut)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToStreetLightsOneOutManipulator.convertAndSaveAsDump(line)
    }
  }
}
