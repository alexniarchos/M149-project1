package com.exercise1.initdb.potholes

import com.exercise1.domain.database.potholes.PotHoles
import com.exercise1.domain.database.potholes.PotHolesRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.FileReader

@Component
class PotHolesManipulator(
  private val potHolesRepository: PotHolesRepository,
  private val dataToPotHolesService: DataToPotHolesService
) : DataManipulator {
  private val logger = LoggerFactory.getLogger(PotHolesManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<PotHoles>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    potHolesRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<PotHoles>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val potHoles = dataToPotHolesService.convertAndSave(line)
      list.add(potHoles)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToPotHolesService.convertAndSaveAsDump(line)
    }
  }
}
