package com.exercise1.domain.database.streetlightsoneout

import com.exercise1.domain.database.shared.Event
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "street_lights_one_out")
data class StreetLightsOneOut(
  @Id
  @Column(name = "event_id")
  var id: Long?,

  @OneToOne
  @JoinColumn(name = "event_id")
  val event: Event? = null
)
