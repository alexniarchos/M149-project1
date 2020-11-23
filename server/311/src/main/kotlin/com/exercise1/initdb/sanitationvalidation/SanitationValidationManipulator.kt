package com.exercise1.initdb.sanitationvalidation

import com.exercise1.domain.database.sanitationviolation.SanitationViolation
import com.exercise1.domain.database.sanitationviolation.SanitationViolationRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class SanitationValidationManipulator(
  private val sanitationViolationRepository: SanitationViolationRepository,
  private val dataToSanitationValidationService: DataToSanitationValidationService
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(SanitationValidationManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<SanitationViolation>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    sanitationViolationRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<SanitationViolation>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val sanitationViolation = dataToSanitationValidationService.convertAndSave(line)
      list.add(sanitationViolation)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToSanitationValidationService.convertAndSaveAsDump(line)
    }
  }
}
