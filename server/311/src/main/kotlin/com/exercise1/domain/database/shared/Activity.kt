package com.exercise1.domain.database.shared

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "activity")
data class Activity(

  @Id
  @Column(name = "event_id")
  var id: Long?,

  @Column(name = "current")
  val current: String?,

  @Column(name = "most_recent")
  val mostRecent: String?
)
