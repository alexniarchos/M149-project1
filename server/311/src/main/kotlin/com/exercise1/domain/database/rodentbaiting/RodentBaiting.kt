package com.exercise1.domain.database.rodentbaiting

import com.exercise1.domain.database.shared.Activity
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
@Table(name = "rodent_baiting")
data class RodentBaiting(
  @Id
  @Column(name = "event_id")
  var id: Long?,

  @Column(name = "number_of_premises_baited")
  val numberOfPremisesBaited: Int?,

  @Column(name = "number_of_premises_with_garbage")
  val numberOfPremisesWithGarbage: Int?,

  @Column(name = "number_of_premises_with_rats")
  val numberOfPremisesWithRats: Int?,

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
