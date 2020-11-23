package com.exercise1.domain.database.shared

import com.exercise1.domain.database.shared.enums.TreeLocationEnum
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(name = "tree_location")
@SequenceGenerator(name = "tree_location_seq", sequenceName = "tree_location_seq", allocationSize = 1)
data class TreeLocation(
  @Id
  @Column(name = "location_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tree_location_seq")
  var id: Long? = null,

  @Enumerated(EnumType.STRING)
  @Column(name = "value", unique = true)
  val value: TreeLocationEnum
)
