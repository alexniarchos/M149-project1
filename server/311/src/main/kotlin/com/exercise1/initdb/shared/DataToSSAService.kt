package com.exercise1.initdb.shared

import com.exercise1.domain.database.shared.SpecialSurveyArea
import com.exercise1.domain.database.shared.SpecialSurveyAreaRepository
import org.springframework.stereotype.Component

@Component
class DataToSSAService(
  private val specialSurveyAreaRepository: SpecialSurveyAreaRepository
) {
  fun convertAndSave(
    line: Array<String?>,
    ssaPosition: Int,
    eventId: Long
  ): SpecialSurveyArea? {
    val value = line[ssaPosition]?.toInt() ?: return null
    val ssa = SpecialSurveyArea(
      eventId,
      value
    )
    return specialSurveyAreaRepository.save(ssa)
  }
}
