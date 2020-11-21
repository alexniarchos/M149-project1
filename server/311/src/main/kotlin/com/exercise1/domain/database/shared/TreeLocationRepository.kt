package com.exercise1.domain.database.shared

import com.exercise1.domain.database.shared.enums.TreeLocationEnum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TreeLocationRepository: JpaRepository<TreeLocation, Long> {
  fun findByValue(value: TreeLocationEnum): TreeLocation?
}
