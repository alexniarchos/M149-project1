package com.exercise1.domain.queries.select

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class EventIdNumberOfOccurrences(
  @Id
  val eventId: Long,
  val numberOfOccurrences: Long
)
