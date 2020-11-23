package com.exercise1.initdb.dump

import com.exercise1.domain.database.dump.Dump
import com.exercise1.domain.database.dump.DumpRepository
import org.springframework.stereotype.Component

@Component
class DataToDumpService(
  private val dumpRepository: DumpRepository
) {
  fun convertAndSave(
    line: Array<String?>,
    creationDatePosition: Int? = null,
    statusPosition: Int? = null,
    completionDatePosition: Int? = null,
    serviceRequestNumberPosition: Int? = null,
    typePosition: Int? = null,
    addressPosition: Int? = null,
    zipCodePosition: Int? = null,
    xCoordinatePosition: Int? = null,
    yCoordinatePosition: Int? = null,
    wardPosition: Int? = null,
    policeDistrictPosition: Int? = null,
    communityAreaPosition: Int? = null,
    latitudePosition: Int? = null,
    longitudePosition: Int? = null,
    historicalWardsPosition: Int? = null,
    zipCodesPosition: Int? = null,
    communityAreasPosition: Int? = null,
    cencusTractsPosition: Int? = null,
    wardsPosition: Int? = null,
    ssaPosition: Int? = null,
    licencePlatePosition: Int? = null,
    modelPosition: Int? = null,
    colorPosition: Int? = null,
    daysStationedPosition: Int? = null,
    numberDeliveredPosition: Int? = null,
    typeOfSurfacePosition: Int? = null,
    locationPosition: Int? = null,
    numberFilledPosition: Int? = null,
    numberOfPremisesBaitedPosition: Int? = null,
    numberOfPremisesWithGarbagePosition: Int? = null,
    numberOfPremisesWithRatsPosition: Int? = null,
    typeOfViolationPosition: Int? = null,
    currentActivityPosition: Int? = null,
    mostRecentActionPosition: Int? = null,
  ): Dump {
    val dump = Dump(
      null,
      creationDatePosition?.let { line[creationDatePosition] },
      statusPosition?.let { line[statusPosition] },
      completionDatePosition?.let { line[completionDatePosition] },
      serviceRequestNumberPosition?.let { line[serviceRequestNumberPosition] },
      typePosition?.let { line[typePosition] },
      addressPosition?.let { line[addressPosition] },
      zipCodePosition?.let { line[zipCodePosition] },
      xCoordinatePosition?.let { line[xCoordinatePosition] },
      yCoordinatePosition?.let { line[yCoordinatePosition] },
      wardPosition?.let { line[wardPosition] },
      policeDistrictPosition?.let { line[policeDistrictPosition] },
      communityAreaPosition?.let { line[communityAreaPosition] },
      latitudePosition?.let { line[latitudePosition] },
      longitudePosition?.let { line[longitudePosition] },
      historicalWardsPosition?.let { line[historicalWardsPosition] },
      zipCodesPosition?.let { line[zipCodesPosition] },
      communityAreasPosition?.let { line[communityAreasPosition] },
      cencusTractsPosition?.let { line[cencusTractsPosition] },
      wardsPosition?.let { line[wardsPosition] },
      ssaPosition?.let { line[ssaPosition] },
      licencePlatePosition?.let { line[licencePlatePosition] },
      modelPosition?.let { line[modelPosition] },
      colorPosition?.let { line[colorPosition] },
      daysStationedPosition?.let { line[daysStationedPosition] },
      numberDeliveredPosition?.let { line[numberDeliveredPosition] },
      typeOfSurfacePosition?.let { line[typeOfSurfacePosition] },
      locationPosition?.let { line[locationPosition] },
      numberFilledPosition?.let { line[numberFilledPosition] },
      numberOfPremisesBaitedPosition?.let { line[numberOfPremisesBaitedPosition] },
      numberOfPremisesWithGarbagePosition?.let { line[numberOfPremisesWithGarbagePosition] },
      numberOfPremisesWithRatsPosition?.let { line[numberOfPremisesWithRatsPosition] },
      typeOfViolationPosition?.let { line[typeOfViolationPosition] },
      currentActivityPosition?.let { line[currentActivityPosition] },
      mostRecentActionPosition?.let { line[mostRecentActionPosition] }
    )
    return dumpRepository.save(dump)
  }
}
