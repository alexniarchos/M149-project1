package com.exercise1.security.authentication

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "user_table")
@SequenceGenerator(name = "user_table_seq", sequenceName = "user_table_seq", allocationSize = 1)
class User(
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_table_seq")
  var id: Long?,
  val username: String,
  val password: String,
  val role: String
)
