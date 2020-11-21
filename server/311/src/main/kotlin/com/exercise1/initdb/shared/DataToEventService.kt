package com.exercise1.initdb.shared

import com.exercise1.domain.database.shared.Event
import com.exercise1.domain.database.shared.EventRepository
import com.exercise1.domain.database.shared.enums.EventStatus
import com.exercise1.domain.database.shared.enums.EventType
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.time.Instant

@Component
class DataToEventService(
  private val eventRepository: EventRepository
) {
  fun convertAndSave(
    line: Array<String?>,
    creationDatePosition: Int,
    statusPosition: Int,
    completionDatePosition: Int,
    serviceRequestNumberPosition: Int,
    typePosition: Int,
    addressPosition: Int,
    zipCodePosition: Int,
    xCoordinatePosition: Int,
    yCoordinatePosition: Int,
    wardPosition: Int,
    policeDistrictPosition: Int,
    communityAreaPosition: Int,
    latitudePosition: Int,
    longitudePosition: Int
  ): Event {
    val event = Event(
      null,
      Instant.parse(line[creationDatePosition] + 'Z'),
      EventStatus.from(line[statusPosition]!!.toLowerCase().replace("\\s".toRegex(), "")),
      getCompletionDate(line[completionDatePosition]),
      line[serviceRequestNumberPosition]!!,
      EventType.from(line[typePosition]!!.toLowerCase().replace("\\s".toRegex(), "")),
      line[addressPosition],
      line[zipCodePosition]?.toInt(),
      line[xCoordinatePosition]?.toBigDecimal(),
      line[yCoordinatePosition]?.toBigDecimal(),
      line[wardPosition]?.toLong(),
      line[policeDistrictPosition]?.toInt(),
      line[communityAreaPosition]?.toInt(),
      line[latitudePosition]?.toBigDecimal(),
      line[longitudePosition]?.toBigDecimal(),
    )
    return eventRepository.save(event)
  }

  private fun getCompletionDate(date: String?): Instant? {
    if (date != null) {
      return Instant.parse(date + 'Z')
    }
    return null
  }
}
