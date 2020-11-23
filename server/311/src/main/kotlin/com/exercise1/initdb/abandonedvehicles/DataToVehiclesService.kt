package com.exercise1.initdb.abandonedvehicles

import com.exercise1.domain.database.abandonedvehicles.AbandonedVehicles
import com.exercise1.domain.database.dump.Dump
import com.exercise1.domain.database.dump.DumpRepository
import com.exercise1.initdb.dump.DataToDumpService
import com.exercise1.initdb.shared.DataToEventService
import com.exercise1.initdb.shared.DataToActivityService
import com.exercise1.initdb.shared.DataToSSAService
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class DataToVehiclesService(
  private val dataToEventService: DataToEventService,
  private val dataToSSAService: DataToSSAService,
  private val dataToActivity: DataToActivityService,
  private val dataToDumpService: DataToDumpService
) {

  companion object {
    private const val CREATION_DATE = 0
    private const val STATUS = 1
    private const val COMPLETION_DATE = 2
    private const val SERVICE_REQUEST_NUMBER = 3
    private const val TYPE_OF_SERVICE = 4
    private const val LICENSE_PLATE = 5
    private const val MODEL = 6
    private const val COLOR = 7
    private const val CURRENT_ACTIVITY = 8
    private const val MOST_RECENT_ACTION = 9
    private const val DAYS_STATIONED = 10
    private const val STREET_ADDRESS = 11
    private const val ZIP_CODE = 12
    private const val X_COORDINATE = 13
    private const val Y_COORDINATE = 14
    private const val WARD = 15
    private const val POLICE_DISTRICT = 16
    private const val COMMUNITY_AREA = 17
    private const val SSA = 18
    private const val LATITUDE = 19
    private const val LONGITUDE = 20
  }

  @Transactional
  fun convertAndSave(line: Array<String?>): AbandonedVehicles {
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
    dataToActivity.convertAndSave(
      line,
      CURRENT_ACTIVITY,
      MOST_RECENT_ACTION,
      event.id!!
    )
    return AbandonedVehicles(
      event.id!!,
      line[LICENSE_PLATE],
      line[MODEL],
      line[COLOR],
      line[DAYS_STATIONED]?.toInt()
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
      mostRecentActionPosition = MOST_RECENT_ACTION,
      currentActivityPosition = CURRENT_ACTIVITY,
      ssaPosition = SSA,
      licencePlatePosition = LICENSE_PLATE,
      modelPosition = MODEL,
      colorPosition = COLOR,
      daysStationedPosition = DAYS_STATIONED
    )
}
