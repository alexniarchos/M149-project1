package com.exercise1.initdb.alleylightsout

import com.exercise1.domain.database.alleylightsout.AlleyLightsOut
import com.exercise1.domain.database.dump.Dump
import com.exercise1.initdb.dump.DataToDumpService
import com.exercise1.initdb.shared.DataToEventService
import com.exercise1.initdb.shared.DataToGeneralInfoService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataToAlleyLightsService(
  private val dataToEventService: DataToEventService,
  private val dataToDumpService: DataToDumpService,
  private val dataToGeneralInfoService: DataToGeneralInfoService
) {
  companion object {
    private const val CREATION_DATE = 0
    private const val STATUS = 1
    private const val COMPLETION_DATE = 2
    private const val SERVICE_REQUEST_NUMBER = 3
    private const val TYPE_OF_SERVICE = 4
    private const val STREET_ADDRESS = 5
    private const val ZIP_CODE = 6
    private const val X_COORDINATE = 7
    private const val Y_COORDINATE = 8
    private const val WARD = 9
    private const val POLICE_DISTRICT = 10
    private const val COMMUNITY_AREA = 11
    private const val LATITUDE = 12
    private const val LONGITUDE = 13
    private const val HISTORICAL_WARDS = 15
    private const val ZIP_CODES = 16
    private const val COMMUNITY_AREAS = 17
    private const val CENCUS_TRACTS = 18
    private const val WARDS = 19
  }

  @Transactional
  fun convertAndSave(line: Array<String?>): AlleyLightsOut {
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
    return AlleyLightsOut(
      event.id!!,
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
      wardsPosition = WARDS
    )
}
