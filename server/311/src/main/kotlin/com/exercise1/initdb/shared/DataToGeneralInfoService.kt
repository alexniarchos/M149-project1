package com.exercise1.initdb.shared

import com.exercise1.domain.database.shared.GeneralInfo
import com.exercise1.domain.database.shared.GeneralInfoRepository
import org.springframework.stereotype.Component

@Component
class DataToGeneralInfoService(
  private val generalInfoRepository: GeneralInfoRepository
) {
  fun convertAndSave(
    line: Array<String?>,
    historicalWardsPosition: Int,
    zipCodesPosition: Int,
    communityAreasPosition: Int,
    cencusTractsPosition: Int,
    wardsPosition: Int,
    eventId: Long
  ): GeneralInfo? {
    val historicalWards = line[historicalWardsPosition]?.toInt()
    val zipCodes = line[zipCodesPosition]?.toInt()
    val communityAreas = line[communityAreasPosition]?.toInt()
    val cencusTracts = line[cencusTractsPosition]?.toInt()
    val wards = line[wardsPosition]?.toInt()
    historicalWards ?: zipCodes ?: communityAreas ?: cencusTracts ?: wards ?: return null
    val generalInfo = GeneralInfo(
      eventId,
      historicalWards,
      zipCodes,
      communityAreas,
      cencusTracts,
      wards
    )
    return generalInfoRepository.save(generalInfo)
  }
}
