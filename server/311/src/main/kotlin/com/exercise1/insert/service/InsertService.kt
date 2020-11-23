package com.exercise1.insert.service

import com.exercise1.domain.database.abandonedvehicles.AbandonedVehicles
import com.exercise1.domain.database.abandonedvehicles.AbandonedVehiclesRepository
import com.exercise1.domain.database.alleylightsout.AlleyLightsOut
import com.exercise1.domain.database.alleylightsout.AlleyLightsOutRepository
import com.exercise1.domain.database.garbagecarts.GarbageCarts
import com.exercise1.domain.database.garbagecarts.GarbageCartsRepository
import com.exercise1.domain.database.graffitiremoval.GraffitiRemoval
import com.exercise1.domain.database.graffitiremoval.GraffitiRemovalRepository
import com.exercise1.domain.database.potholes.PotHoles
import com.exercise1.domain.database.potholes.PotHolesRepository
import com.exercise1.domain.database.rodentbaiting.RodentBaiting
import com.exercise1.domain.database.rodentbaiting.RodentBaitingRepository
import com.exercise1.domain.database.sanitationviolation.SanitationViolation
import com.exercise1.domain.database.sanitationviolation.SanitationViolationRepository
import com.exercise1.domain.database.shared.Activity
import com.exercise1.domain.database.shared.ActivityRepository
import com.exercise1.domain.database.shared.Event
import com.exercise1.domain.database.shared.EventRepository
import com.exercise1.domain.database.shared.GeneralInfo
import com.exercise1.domain.database.shared.GeneralInfoRepository
import com.exercise1.domain.database.shared.SpecialSurveyArea
import com.exercise1.domain.database.shared.SpecialSurveyAreaRepository
import com.exercise1.domain.database.shared.TreeLocation
import com.exercise1.domain.database.shared.TreeLocationRepository
import com.exercise1.domain.database.shared.enums.EventType
import com.exercise1.domain.database.streetlightsallout.StreetLightsAllOut
import com.exercise1.domain.database.streetlightsallout.StreetLightsAllOutRepository
import com.exercise1.domain.database.streetlightsoneout.StreetLightsOneOut
import com.exercise1.domain.database.streetlightsoneout.StreetLightsOneOutRepository
import com.exercise1.domain.database.treedebris.TreeDebris
import com.exercise1.domain.database.treedebris.TreeDebrisRepository
import com.exercise1.domain.database.treetrims.TreeTrims
import com.exercise1.domain.database.treetrims.TreeTrimsRepository
import com.exercise1.insert.api.InsertInputDto
import com.exercise1.security.UserPrinciple
import com.exercise1.useraction.ActionData
import com.exercise1.useraction.ActionEnum
import com.exercise1.useraction.UserAction
import com.exercise1.useraction.UserActionRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class InsertService(
  private val eventRepository: EventRepository,
  private val activityRepository: ActivityRepository,
  private val generalInfoRepository: GeneralInfoRepository,
  private val specialSurveyAreaRepository: SpecialSurveyAreaRepository,
  private val treeLocationRepository: TreeLocationRepository,
  private val abandonedVehiclesRepository: AbandonedVehiclesRepository,
  private val alleyLightsOutRepository: AlleyLightsOutRepository,
  private val garbageCartsRepository: GarbageCartsRepository,
  private val graffitiRemovalRepository: GraffitiRemovalRepository,
  private val potHolesRepository: PotHolesRepository,
  private val rodentBaitingRepository: RodentBaitingRepository,
  private val sanitationViolationRepository: SanitationViolationRepository,
  private val streetLightsAllOutRepository: StreetLightsAllOutRepository,
  private val streetLightsOneOutRepository: StreetLightsOneOutRepository,
  private val treeDebrisRepository: TreeDebrisRepository,
  private val treeTrimsRepository: TreeTrimsRepository,
  private val userActionRepository: UserActionRepository
) {

  fun route(insertInputDto: InsertInputDto): Any? {
    saveUserAction(insertInputDto)
    return when (insertInputDto.type) {
      EventType.ABANDONED_VEHICLES -> insertAbandonedVehicle(insertInputDto)
      EventType.ALLEY_LIGHT_OUT -> insertAlleyLightsOut(insertInputDto)
      EventType.GARBAGE_CART -> insertGarbageCarts(insertInputDto)
      EventType.GRAFFITI_REMOVAL -> insertGraffitiRemoval(insertInputDto)
      EventType.POTHOLE_IN_STREET -> insertPotHole(insertInputDto)
      EventType.RODENT_BAITING -> insertRodentBaiting(insertInputDto)
      EventType.SANITATION_VIOLATION -> insertSanitationViolation(insertInputDto)
      EventType.STREET_LIGHTS_ALL_OUT -> insertStreetLightsAllOut(insertInputDto)
      EventType.STREET_LIGHT_OUT -> insertStreetLightOut(insertInputDto)
      EventType.TREE_DEBRIS -> insertTreeDebris(insertInputDto)
      EventType.TREE_TRIM -> insertTreeTrims(insertInputDto)
    }
  }

  private fun saveUserAction(insertInputDto: InsertInputDto) {
    val userId = (SecurityContextHolder.getContext().authentication.principal as UserPrinciple).id
    userActionRepository.save(
      UserAction(
        null,
        userId,
        ActionEnum.INSERT,
        insertInputDto.type.name,
        ActionData(insertData = insertInputDto)
      )
    )
  }

  private fun insertAbandonedVehicle(insertInputDto: InsertInputDto): AbandonedVehicles {
    val event = insertEvent(insertInputDto)
    val ssa = insertSSA(insertInputDto, event.id!!)
    val activity = insertActivity(insertInputDto, event.id!!)
    return abandonedVehiclesRepository.save(
      AbandonedVehicles(
        event.id!!,
        insertInputDto.licencePlate,
        insertInputDto.model,
        insertInputDto.color,
        insertInputDto.daysStationed
      )
    ).copy(specialSurveyArea = ssa, activity = activity, event = event)
  }

  private fun insertAlleyLightsOut(insertInputDto: InsertInputDto): AlleyLightsOut {
    val event = insertEvent(insertInputDto)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    return alleyLightsOutRepository.save(
      AlleyLightsOut(event.id!!)
    ).copy(generalInfo = generalInfo, event = event)
  }

  private fun insertGarbageCarts(insertInputDto: InsertInputDto): GarbageCarts {
    val event = insertEvent(insertInputDto)
    val ssa = insertSSA(insertInputDto, event.id!!)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    val activity = insertActivity(insertInputDto, event.id!!)
    return garbageCartsRepository.save(
      GarbageCarts(event.id!!, insertInputDto.numberDelivered)
    ).copy(specialSurveyArea = ssa, activity = activity, generalInfo = generalInfo, event = event)
  }

  private fun insertGraffitiRemoval(insertInputDto: InsertInputDto): GraffitiRemoval {
    val event = insertEvent(insertInputDto)
    val ssa = insertSSA(insertInputDto, event.id!!)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    return graffitiRemovalRepository.save(
      GraffitiRemoval(
        event.id!!, 
        insertInputDto.typeOfSurface, 
        insertInputDto.graffitiLocation
      )
    ).copy(specialSurveyArea = ssa, generalInfo = generalInfo, event = event)
  }

  private fun insertPotHole(insertInputDto: InsertInputDto): PotHoles {
    val event = insertEvent(insertInputDto)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    val activity = insertActivity(insertInputDto, event.id!!)
    val ssa = insertSSA(insertInputDto, event.id!!)
    return potHolesRepository.save(
      PotHoles(event.id!!, insertInputDto.numberFilled)
    ).copy(specialSurveyArea = ssa, activity = activity, generalInfo = generalInfo, event = event)
  }

  private fun insertRodentBaiting(insertInputDto: InsertInputDto): RodentBaiting {
    val event = insertEvent(insertInputDto)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    val activity = insertActivity(insertInputDto, event.id!!)
    return rodentBaitingRepository.save(
      RodentBaiting(
        event.id!!,
        insertInputDto.numberOfPremisesBaited,
        insertInputDto.numberOfPremisesWithGarbage,
        insertInputDto.numberOfPremisesWithRats
      )
    ).copy(activity = activity, generalInfo = generalInfo, event = event)
  }

  private fun insertSanitationViolation(insertInputDto: InsertInputDto): SanitationViolation {
    val event = insertEvent(insertInputDto)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    return sanitationViolationRepository.save(
      SanitationViolation(event.id!!, insertInputDto.typeOfViolation)
    ).copy(generalInfo = generalInfo, event = event)
  }

  private fun insertStreetLightsAllOut(insertInputDto: InsertInputDto): StreetLightsAllOut {
    val event = insertEvent(insertInputDto)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    return streetLightsAllOutRepository.save(
      StreetLightsAllOut(event.id!!)
    ).copy(generalInfo = generalInfo, event = event)
  }

  private fun insertStreetLightOut(insertInputDto: InsertInputDto): StreetLightsOneOut {
    val event = insertEvent(insertInputDto)
    return streetLightsOneOutRepository.save(
      StreetLightsOneOut(event.id!!)
    ).copy(event = event)
  }

  private fun insertTreeDebris(insertInputDto: InsertInputDto): TreeDebris {
    val event = insertEvent(insertInputDto)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    val activity = insertActivity(insertInputDto, event.id!!)
    val treeLocation = insertTreeLocation(insertInputDto)
    return treeDebrisRepository.save(
      TreeDebris(event.id, treeLocation)
    ).copy(activity = activity, generalInfo = generalInfo, event = event)
  }

  private fun insertTreeTrims(insertInputDto: InsertInputDto): TreeTrims {
    val event = insertEvent(insertInputDto)
    val generalInfo = insertGeneralInfo(insertInputDto, event.id!!)
    val treeLocation = insertTreeLocation(insertInputDto)
    return treeTrimsRepository.save(
      TreeTrims(event.id, treeLocation)
    ).copy(generalInfo = generalInfo, event = event)
  }

  private fun insertEvent(insertInputDto: InsertInputDto): Event =
    eventRepository.save(
      Event(
        null,
        Instant.now(),
        insertInputDto.status,
        insertInputDto.completionDate,
        insertInputDto.serviceRequestNumber,
        insertInputDto.type,
        insertInputDto.address,
        insertInputDto.zipCode,
        insertInputDto.customCoordinateX,
        insertInputDto.customCoordinateY,
        insertInputDto.ward,
        insertInputDto.policeDistrict,
        insertInputDto.communityArea,
        insertInputDto.latitude,
        insertInputDto.longitude
      )
    )

  private fun insertGeneralInfo(insertInputDto: InsertInputDto, eventId: Long) =
    generalInfoRepository.save(
      GeneralInfo(
        eventId,
        insertInputDto.historicalWards,
        insertInputDto.zipCodes,
        insertInputDto.communityAreas,
        insertInputDto.cencusTracts,
        insertInputDto.wards
      )
    )

  private fun insertActivity(insertInputDto: InsertInputDto, eventId: Long) =
    activityRepository.save(Activity(eventId, insertInputDto.currentActivity, insertInputDto.mostRecentAction))

  private fun insertSSA(insertInputDto: InsertInputDto, eventId: Long) =
    specialSurveyAreaRepository.save(SpecialSurveyArea(eventId, insertInputDto.ssa))

  private fun insertTreeLocation(insertInputDto: InsertInputDto): TreeLocation =
    treeLocationRepository.findByValue(insertInputDto.treeLocation!!)
      ?: treeLocationRepository.save(TreeLocation(null, insertInputDto.treeLocation))

}
