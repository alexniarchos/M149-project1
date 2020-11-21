package com.exercise1.domain.database.streetlightsallout

import com.exercise1.domain.database.shared.Event
import com.exercise1.domain.database.shared.GeneralInfo
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "street_lights_all_out")
data class StreetLightsAllOut(
  @Id
  @Column(name = "event_id")
  var id: Long?,

  @OneToOne
  @JoinColumn(name = "event_id")
  val generalInfo: GeneralInfo? = null,

  @OneToOne
  @JoinColumn(name = "event_id")
  val event: Event? = null
)
