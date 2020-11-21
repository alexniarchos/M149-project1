package com.exercise1.initdb.alleylightsout

import com.exercise1.domain.database.alleylightsout.AlleyLightsOut
import com.exercise1.domain.database.alleylightsout.AlleyLightsOutRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class AlleyLightsOutManipulator(
  private val alleyLightsOutRepository: AlleyLightsOutRepository,
  private val dataToAlleyLightsService: DataToAlleyLightsService
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(AlleyLightsOutManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<AlleyLightsOut>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    alleyLightsOutRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<AlleyLightsOut>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val alleyLightsOut = dataToAlleyLightsService.convertAndSave(line)
      list.add(alleyLightsOut)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToAlleyLightsService.convertAndSaveAsDump(line)
    }
  }
}
