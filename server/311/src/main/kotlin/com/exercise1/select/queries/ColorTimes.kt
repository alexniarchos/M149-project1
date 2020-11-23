package com.exercise1.domain.queries.select

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ColorTimes(
  @Id
  val color: String,
  val times: Long
)
