package com.exercise1.initdb.potholes

import com.exercise1.domain.database.dump.Dump
import com.exercise1.domain.database.garbagecarts.GarbageCarts
import com.exercise1.domain.database.potholes.PotHoles
import com.exercise1.initdb.dump.DataToDumpService
import com.exercise1.initdb.garbagecarts.DataToGarbageCartsService
import com.exercise1.initdb.shared.DataToActivityService
import com.exercise1.initdb.shared.DataToEventService
import com.exercise1.initdb.shared.DataToGeneralInfoService
import com.exercise1.initdb.shared.DataToSSAService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataToPotHolesService(
  private val dataToSSAService: DataToSSAService,
  private val dataToEventService: DataToEventService,
  private val dataToGeneralInfoService: DataToGeneralInfoService,
  private val dataToActivityService: DataToActivityService,
  private val dataToDumpService: DataToDumpService
) {
  companion object {
    private const val CREATION_DATE = 0
    private const val STATUS = 1
    private const val COMPLETION_DATE = 2
    private const val SERVICE_REQUEST_NUMBER = 3
    private const val TYPE_OF_SERVICE = 4
    private const val CURRENT_ACTIVITY = 5
    private const val MOST_RECENT_ACTION = 6
    private const val NUMBERS_FILLED = 7
    private const val STREET_ADDRESS = 8
    private const val ZIP_CODE = 9
    private const val X_COORDINATE = 10
    private const val Y_COORDINATE = 11
    private const val WARD = 12
    private const val POLICE_DISTRICT = 13
    private const val COMMUNITY_AREA = 14
    private const val SSA = 15
    private const val LATITUDE = 16
    private const val LONGITUDE = 17
    private const val HISTORICAL_WARDS = 19
    private const val ZIP_CODES = 20
    private const val COMMUNITY_AREAS = 21
    private const val CENCUS_TRACTS = 22
    private const val WARDS = 23
  }

  @Transactional
  fun convertAndSave(line: Array<String?>): PotHoles {
    val event = dataToEventService.convertAndSave(
      line,
      CREATION_DATE,
      STATUS,
      COMPLETION_DATE,
      SERVICE_REQUEST_NUMBER,
      TYPE_OF_SERVICE,
      STREET_ADDRESS,
      ZIP_CODE,
      X_COORDINATE,
      Y_COORDINATE,
      WARD,
      POLICE_DISTRICT,
      COMMUNITY_AREA,
      LATITUDE,
      LONGITUDE
    )
    dataToSSAService.convertAndSave(line, SSA, event.id!!)
    dataToGeneralInfoService.convertAndSave(
      line,
      HISTORICAL_WARDS,
      ZIP_CODES,
      COMMUNITY_AREAS,
      CENCUS_TRACTS,
      WARDS,
      event.id!!
    )
    dataToActivityService.convertAndSave(
      line,
      CURRENT_ACTIVITY,
      MOST_RECENT_ACTION,
      event.id!!
    )
    return PotHoles(
      event.id!!,
      line[NUMBERS_FILLED]?.toInt()
    )
  }

  fun convertAndSaveAsDump(line: Array<String?>): Dump =
    dataToDumpService.convertAndSave(
      line = line,
      creationDatePosition = CREATION_DATE,
      statusPosition = STATUS,
      completionDatePosition = COMPLETION_DATE,
      serviceRequestNumberPosition = SERVICE_REQUEST_NUMBER,
      typePosition = TYPE_OF_SERVICE,
      addressPosition = STREET_ADDRESS,
      zipCodePosition = ZIP_CODE,
      xCoordinatePosition = X_COORDINATE,
      yCoordinatePosition = Y_COORDINATE,
      wardPosition = WARD,
      policeDistrictPosition = POLICE_DISTRICT,
      communityAreaPosition = COMMUNITY_AREA,
      latitudePosition = LATITUDE,
      longitudePosition = LONGITUDE,
      historicalWardsPosition = HISTORICAL_WARDS,
      zipCodesPosition = ZIP_CODES,
      communityAreasPosition = COMMUNITY_AREAS,
      cencusTractsPosition = CENCUS_TRACTS,
      wardsPosition = WARDS,
      ssaPosition = SSA,
      numberFilledPosition = NUMBERS_FILLED,
      mostRecentActionPosition = MOST_RECENT_ACTION,
      currentActivityPosition = CURRENT_ACTIVITY
    )
}
