package com.exercise1.select.queries

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DateTimes(
  @Id
  val date: String,
  val times: Long
)
