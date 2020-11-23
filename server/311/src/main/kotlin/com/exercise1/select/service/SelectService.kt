package com.exercise1.select.service

import com.exercise1.domain.database.shared.EventRepository
import com.exercise1.insert.api.InsertInputDto
import com.exercise1.security.UserPrinciple
import com.exercise1.select.api.SelectQueryEnum
import com.exercise1.select.api.SelectInputDto
import com.exercise1.select.queries.ColorTimesRepository
import com.exercise1.select.queries.DateSSATimesRepository
import com.exercise1.select.queries.DateTimesRepository
import com.exercise1.select.queries.EventIdNumberOfOccurrencesRepository
import com.exercise1.select.queries.LicencePlateRepository
import com.exercise1.select.queries.PoliceDistrictRepository
import com.exercise1.select.queries.TypeCompletionTimeRepository
import com.exercise1.select.queries.TypeTimesRepository
import com.exercise1.select.queries.ZipCodeTypeTimesRepository
import com.exercise1.useraction.ActionData
import com.exercise1.useraction.ActionEnum
import com.exercise1.useraction.UserAction
import com.exercise1.useraction.UserActionRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SelectService(
  private val colorTimesRepository: ColorTimesRepository,
  private val dateSSATimesRepository: DateSSATimesRepository,
  private val dateTimesRepository: DateTimesRepository,
  private val eventIdNumberOfOccurrencesRepository: EventIdNumberOfOccurrencesRepository,
  private val licencePlateRepository: LicencePlateRepository,
  private val policeDistrictRepository: PoliceDistrictRepository,
  private val typeCompletionTimeRepository: TypeCompletionTimeRepository,
  private val typeTimesRepository: TypeTimesRepository,
  private val zipCodeTypeTimesRepository: ZipCodeTypeTimesRepository,
  private val eventRepository: EventRepository,
  private val userActionRepository: UserActionRepository
) {
  fun route(selectInputDto: SelectInputDto): Any? {
    saveUserAction(selectInputDto)
    return when(selectInputDto.query) {
      SelectQueryEnum.TOTAL_REQUESTS_SPECIFIED_RANGE -> typeTimesRepository.findTotalRequestsPerType(selectInputDto.minRange!!, selectInputDto.maxRange!!)
      SelectQueryEnum.TOTAL_REQUESTS_PER_DAY_AND_TIME_RANGE -> dateTimesRepository.findTotalRequestsPerDayForType(selectInputDto.minRange!!, selectInputDto.maxRange!!, selectInputDto.type!!.name)
      SelectQueryEnum.MOST_COMMON_SERVICE_REQUEST_PER_TYPE_AND_TIME_RANGE -> zipCodeTypeTimesRepository.findMostCommonServiceRequestPerZipCode(selectInputDto.day!!)
      SelectQueryEnum.AVERAGE_COMPLETION_TIME_PER_SERVICE_REQUEST_AND_TIME_RANGE -> typeCompletionTimeRepository.findAverageCompletionTimePerDay(selectInputDto.minRange!!, selectInputDto.maxRange!!)
      SelectQueryEnum.MOST_COMMON_REQUEST_IN_SPECIFIED_GPS_COORDINATES ->
        typeTimesRepository.findMostCommonServiceInPlace(selectInputDto.minLatitude!!, selectInputDto.minLongitude!!, selectInputDto.maxLatitude!!, selectInputDto.maxLongitude!!, selectInputDto.day!!)
      SelectQueryEnum.TOP_FIVE_SSA_PER_DAY_FOR_SPECIFIED_TIME_RANGE -> dateSSATimesRepository.findTopFiveSSAPerDay(selectInputDto.minRange!!, selectInputDto.maxRange!!)
      SelectQueryEnum.LICENCE_PLATES_ABANDONED_MORE_THAN_ONCE -> licencePlateRepository.findLicencePlatesAbandonedMultipleTimes()
      SelectQueryEnum.SECOND_MOST_COMMON_COLOR_ABANDONED_VEHICLES -> colorTimesRepository.findSecondMostCommonColorOfAbandonedVehicles()
      SelectQueryEnum.NUMBER_OF_PREMISES_BAITED_LESS_THAN -> eventIdNumberOfOccurrencesRepository.findPremisesLesserThan(selectInputDto.numberOfPremises!!)
      SelectQueryEnum.NUMBER_OF_PREMISES_WITH_GARBAGE_LESS_THAN -> eventIdNumberOfOccurrencesRepository.findPremisesWithGarbageLesserThan(selectInputDto.numberOfPremises!!)
      SelectQueryEnum.NUMBER_OF_PREMISES_WITH_RATS_LESS_THAN -> eventIdNumberOfOccurrencesRepository.findPremisesWithRatsLesserThan(selectInputDto.numberOfPremises!!)
      SelectQueryEnum.POLICE_DISTRICTS_HANDLED_MORE_THAN_ONE_POT_HOLES_AND_RODENT_BAITING -> policeDistrictRepository.findPoliceDistrictPotHolesRodentBaiting(selectInputDto.day!!)
      SelectQueryEnum.EVENTS_IN_SPECIFIED_STREET -> eventRepository.findAllByAddressContains(selectInputDto.street!!)
      SelectQueryEnum.EVENTS_IN_SPECIFIED_ZIP_CODE -> eventRepository.findAllByZipCode(selectInputDto.zipCode!!)
    }
  }

  private fun saveUserAction(selectInputDto: SelectInputDto) {
    val userId = (SecurityContextHolder.getContext().authentication.principal as UserPrinciple).id
    userActionRepository.save(
      UserAction(
        null,
        userId,
        ActionEnum.SELECT,
        selectInputDto.query.name,
        ActionData(selectData = selectInputDto)
      )
    )
  }
}
