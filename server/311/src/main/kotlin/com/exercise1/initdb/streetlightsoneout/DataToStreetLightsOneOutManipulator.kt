package com.exercise1.initdb.streetlightsoneout

import com.exercise1.domain.database.dump.Dump
import com.exercise1.domain.database.streetlightsoneout.StreetLightsOneOut
import com.exercise1.initdb.dump.DataToDumpService
import com.exercise1.initdb.shared.DataToEventService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataToStreetLightsOneOutManipulator(
  private val dataToEventService: DataToEventService,
  private val dataToDumpService: DataToDumpService
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
  }

  @Transactional
  fun convertAndSave(line: Array<String?>): StreetLightsOneOut {
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
    return StreetLightsOneOut(
      event.id!!
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
    )
}
