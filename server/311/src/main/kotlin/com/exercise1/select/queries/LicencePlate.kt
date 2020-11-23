package com.exercise1.select.queries

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class LicencePlate(
  @Id
  val plate: String,

  val times: Long
)
