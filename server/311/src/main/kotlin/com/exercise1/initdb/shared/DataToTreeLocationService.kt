package com.exercise1.initdb.shared

import com.exercise1.domain.database.shared.TreeLocation
import com.exercise1.domain.database.shared.TreeLocationRepository
import com.exercise1.domain.database.shared.enums.TreeLocationEnum
import org.springframework.stereotype.Component

@Component
class DataToTreeLocationService(
  private val treeLocationRepository: TreeLocationRepository
) {
  fun convertAndSave(
    line: Array<String?>,
    locationPosition: Int
  ): TreeLocation? {
    val location = line[locationPosition] ?: return null
    val value = TreeLocationEnum.from(location.toLowerCase().replace("\\s".toRegex(), ""))
    return treeLocationRepository.findByValue(value) ?: treeLocationRepository.save(TreeLocation(null, value))
  }
}
