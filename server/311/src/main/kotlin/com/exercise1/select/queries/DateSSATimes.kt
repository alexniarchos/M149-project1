package com.exercise1.domain.queries.select

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class DateSSATimes(
  @Id
  val date: String,
  val ssa: Int,
  val times: Long
)
