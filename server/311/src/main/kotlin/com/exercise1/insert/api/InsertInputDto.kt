package com.exercise1.insert.api

import com.exercise1.domain.database.shared.enums.EventStatus
import com.exercise1.domain.database.shared.enums.EventType
import com.exercise1.domain.database.shared.enums.TreeLocationEnum
import java.math.BigDecimal
import java.time.Instant

data class InsertInputDto(
  val status: EventStatus,
  val completionDate: Instant?,
  val serviceRequestNumber: String,
  val type: EventType,
  val address: String?,
  val zipCode: Int?,
  val customCoordinateX: BigDecimal?,
  val customCoordinateY: BigDecimal?,
  val ward: Long?,
  val policeDistrict: Int?,
  val communityArea: Int?,
  val latitude: BigDecimal?,
  val longitude: BigDecimal?,
  val historicalWards: Int?,
  val zipCodes: Int?,
  val communityAreas: Int?,
  val cencusTracts: Int?,
  val wards: Int?,
  val ssa: Int?,
  val licencePlate: String?,
  val model: String?,
  val color: String?,
  val daysStationed: Int?,
  val numberDelivered: Long?,
  val typeOfSurface: String?,
  val graffitiLocation: String?,
  val treeLocation: TreeLocationEnum?,
  val numberFilled: Int?,
  val numberOfPremisesBaited: Int?,
  val numberOfPremisesWithGarbage: Int?,
  val numberOfPremisesWithRats: Int?,
  val typeOfViolation: String?,
  val currentActivity: String?,
  val mostRecentAction: String?
)
