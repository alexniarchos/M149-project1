package com.exercise1.initdb.rodentbaiting

import com.exercise1.domain.database.rodentbaiting.RodentBaiting
import com.exercise1.domain.database.rodentbaiting.RodentBaitingRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class RodentBaitingManipulator(
  private val rodentBaitingRepository: RodentBaitingRepository,
  private val dataToRodentBaitingService: DataToRodentBaitingService
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(RodentBaitingManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<RodentBaiting>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    rodentBaitingRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<RodentBaiting>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val rodentBaiting = dataToRodentBaitingService.convertAndSave(line)
      list.add(rodentBaiting)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToRodentBaitingService.convertAndSaveAsDump(line)
    }
  }
}
