package com.exercise1.domain.database.abandonedvehicles

import com.exercise1.domain.database.shared.Activity
import com.exercise1.domain.database.shared.Event
import com.exercise1.domain.database.shared.SpecialSurveyArea
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "abandoned_vehicles")
data class AbandonedVehicles(
  @Id
  @Column(name = "event_id")
  var id: Long?,

  @Column(name = "licence_plate", length = 512)
  val licencePlate: String?,

  @Column(name = "model")
  val model: String?,

  @Column(name = "color")
  val color: String?,

  @Column(name = "days_stationed")
  val daysStationed: Int?,

  @OneToOne
  @JoinColumn(name = "event_id")
  val specialSurveyArea: SpecialSurveyArea? = null,

  @OneToOne
  @JoinColumn(name = "event_id")
  val activity: Activity? = null,

  @OneToOne
  @JoinColumn(name = "event_id")
  val event: Event? = null
)
