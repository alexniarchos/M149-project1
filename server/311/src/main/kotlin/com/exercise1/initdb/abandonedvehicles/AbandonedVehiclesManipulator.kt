package com.exercise1.initdb.abandonedvehicles

import com.exercise1.domain.database.abandonedvehicles.AbandonedVehicles
import com.exercise1.domain.database.abandonedvehicles.AbandonedVehiclesRepository
import com.exercise1.initdb.DataManipulator
import com.opencsv.CSVReader
import com.opencsv.CSVReaderBuilder
import com.opencsv.enums.CSVReaderNullFieldIndicator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.FileReader

@Service
class AbandonedVehiclesManipulator(
  private val abandonedVehiclesRepository: AbandonedVehiclesRepository,
  private val dataToVehiclesService: DataToVehiclesService
) : DataManipulator {

  private val logger = LoggerFactory.getLogger(AbandonedVehiclesManipulator::class.java)

  companion object {
    private const val FIRST = 1
  }

  override fun manipulate(filepath: String) {
    val fileReader = BufferedReader(FileReader(filepath))
    val reader = CSVReaderBuilder(fileReader).withFieldAsNull(CSVReaderNullFieldIndicator.BOTH).build()
    val list = mutableListOf<AbandonedVehicles>()
    reader.skip(FIRST)
    while (parseNextLine(list, reader) != null) {}
    reader.close()
    fileReader.close()
    abandonedVehiclesRepository.saveAll(list)
  }

  private fun parseNextLine(list: MutableList<AbandonedVehicles>, reader: CSVReader): Any? {
    val line = reader.readNext() ?: return null
    return try {
      val abandonedVehicles = dataToVehiclesService.convertAndSave(line)
      list.add(abandonedVehicles)
    } catch (e: Exception) {
      logger.error("could not save", e)
      dataToVehiclesService.convertAndSaveAsDump(line)
    }
  }
}
