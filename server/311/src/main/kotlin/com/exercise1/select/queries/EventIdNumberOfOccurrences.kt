package com.exercise1.select.queries

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class EventIdNumberOfOccurrences(
  @Id
  val eventId: Long,
  val number: Long
)
