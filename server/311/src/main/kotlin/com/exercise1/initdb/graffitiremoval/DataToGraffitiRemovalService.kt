package com.exercise1.initdb.graffitiremoval

import com.exercise1.domain.database.dump.Dump
import com.exercise1.domain.database.graffitiremoval.GraffitiRemoval
import com.exercise1.initdb.dump.DataToDumpService
import com.exercise1.initdb.shared.DataToEventService
import com.exercise1.initdb.shared.DataToGeneralInfoService
import com.exercise1.initdb.shared.DataToSSAService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataToGraffitiRemovalService(
  private val dataToSSAService: DataToSSAService,
  private val dataToGeneralInfoService: DataToGeneralInfoService,
  private val dataToEventService: DataToEventService,
  private val dataToDumpService: DataToDumpService
) {

  companion object {
    private const val CREATION_DATE = 0
    private const val STATUS = 1
    private const val COMPLETION_DATE = 2
    private const val SERVICE_REQUEST_NUMBER = 3
    private const val TYPE_OF_SERVICE = 4
    private const val SURFACE_TYPE = 5
    private const val LOCATION = 6
    private const val STREET_ADDRESS = 7
    private const val ZIP_CODE = 8
    private const val X_COORDINATE = 9
    private const val Y_COORDINATE = 10
    private const val WARD = 11
    private const val POLICE_DISTRICT = 12
    private const val COMMUNITY_AREA = 13
    private const val SSA = 14
    private const val LATITUDE = 15
    private const val LONGITUDE = 16
    private const val HISTORICAL_WARDS = 18
    private const val ZIP_CODES = 19
    private const val COMMUNITY_AREAS = 20
    private const val CENCUS_TRACTS = 21
    private const val WARDS = 22
  }

  @Transactional
  fun convertAndSave(line: Array<String?>): GraffitiRemoval {
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
    return GraffitiRemoval(
      event.id!!,
      line[SURFACE_TYPE],
      line[LOCATION]
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
      typeOfSurfacePosition = SURFACE_TYPE,
      locationPosition = LOCATION
    )

}
