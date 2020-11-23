package com.exercise1.domain.queries.select

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class PoliceDistrict(
  @Id
  val policeDistrict: Long
)
