package com.exercise1.domain.database.potholes

import com.exercise1.domain.database.shared.Activity
import com.exercise1.domain.database.shared.Event
import com.exercise1.domain.database.shared.GeneralInfo
import com.exercise1.domain.database.shared.SpecialSurveyArea
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "pot_holes")
data class PotHoles(
  @Id
  @Column(name = "event_id")
  var id: Long?,

  @Column(name = "number_filled")
  val numberFilled: Int?,

  @OneToOne
  @JoinColumn(name = "event_id")
  val specialSurveyArea: SpecialSurveyArea? = null,

  @OneToOne
  @JoinColumn(name = "event_id")
  val activity: Activity? = null,

  @OneToOne
  @JoinColumn(name = "event_id")
  val generalInfo: GeneralInfo? = null,

  @OneToOne
  @JoinColumn(name = "event_id")
  val event: Event? = null
)
