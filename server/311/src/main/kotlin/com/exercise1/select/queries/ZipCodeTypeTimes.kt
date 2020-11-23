package com.exercise1.domain.queries.select

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class ZipCodeTypeTimes(
  @Id
  val zipCode: Long,
  val type: String,
  val times: Long
)
