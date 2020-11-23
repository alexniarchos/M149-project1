package com.exercise1.select.queries

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class TypeTimes(
  @Id
  val type: String,
  val times: Long
)
