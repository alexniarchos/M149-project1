package com.exercise1.select.queries

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class TypeCompletionTime(
  @Id
  val type: String,
  val time: String
)
