package com.exercise1.initdb.rodentbaiting

import com.exercise1.domain.database.dump.Dump
import com.exercise1.domain.database.rodentbaiting.RodentBaiting
import com.exercise1.initdb.dump.DataToDumpService
import com.exercise1.initdb.shared.DataToActivityService
import com.exercise1.initdb.shared.DataToEventService
import com.exercise1.initdb.shared.DataToGeneralInfoService
import com.exercise1.initdb.shared.DataToSSAService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataToRodentBaitingService(
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
    private const val NUMBER_OF_PREMISES_BAITED = 5
    private const val NUMBER_OF_PREMISES_WITH_GARBAGE = 6
    private const val NUMBER_OF_PREMISES_WITH_RATS = 7
    private const val CURRENT_ACTIVITY = 8
    private const val MOST_RECENT_ACTION = 9
    private const val STREET_ADDRESS = 10
    private const val ZIP_CODE = 11
    private const val X_COORDINATE = 12
    private const val Y_COORDINATE = 13
    private const val WARD = 14
    private const val POLICE_DISTRICT = 15
    private const val COMMUNITY_AREA = 16
    private const val LATITUDE = 17
    private const val LONGITUDE = 18
    private const val HISTORICAL_WARDS = 20
    private const val ZIP_CODES = 21
    private const val COMMUNITY_AREAS = 22
    private const val CENCUS_TRACTS = 23
    private const val WARDS = 24
  }

  @Transactional
  fun convertAndSave(line: Array<String?>): RodentBaiting {
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
    return RodentBaiting(
      event.id!!,
      line[NUMBER_OF_PREMISES_BAITED]?.toInt(),
      line[NUMBER_OF_PREMISES_WITH_GARBAGE]?.toInt(),
      line[NUMBER_OF_PREMISES_WITH_RATS]?.toInt()
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
      mostRecentActionPosition = MOST_RECENT_ACTION,
      currentActivityPosition = CURRENT_ACTIVITY,
      numberOfPremisesBaitedPosition = NUMBER_OF_PREMISES_BAITED,
      numberOfPremisesWithRatsPosition = NUMBER_OF_PREMISES_WITH_RATS,
      numberOfPremisesWithGarbagePosition = NUMBER_OF_PREMISES_WITH_GARBAGE
    )
}
