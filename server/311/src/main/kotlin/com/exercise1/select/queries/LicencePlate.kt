package com.exercise1.domain.queries.select

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class LicencePlate(
  @Id
  val licencePlate: String
)
