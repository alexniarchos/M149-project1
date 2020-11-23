package com.exercise1.initdb.streetlightsallout

import com.exercise1.domain.database.streetlightsallout.StreetLightsAllOut
import com.exercise1.domain.database.streetlightsallout.StreetLightsAllOutRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class StreetLightsAllOutManipulator(
  private val streetLightsAllOutRepository: StreetLightsAllOutRepository,
  private val dataToStreetLightsAllOutService: DataToStreetLightsAllOutService
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(StreetLightsAllOutManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<StreetLightsAllOut>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    streetLightsAllOutRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<StreetLightsAllOut>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val streetLightsAllOut = dataToStreetLightsAllOutService.convertAndSave(line)
      list.add(streetLightsAllOut)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToStreetLightsAllOutService.convertAndSaveAsDump(line)
    }
  }
}
