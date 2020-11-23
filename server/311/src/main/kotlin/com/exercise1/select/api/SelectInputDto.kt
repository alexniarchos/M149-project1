package com.exercise1.select.api

import com.exercise1.domain.database.shared.enums.EventType
import java.math.BigDecimal
import java.time.Instant

data class SelectInputDto(
  val query: SelectQueryEnum,
  val day: Instant?,
  val minRange: Instant?,
  val maxRange: Instant?,
  val numberOfPremises: Long?,
  val type: EventType?,
  val minLatitude: BigDecimal?,
  val maxLatitude: BigDecimal?,
  val minLongitude: BigDecimal?,
  val maxLongitude: BigDecimal?,
  val zipCode: Int?,
  val street: String?
)
