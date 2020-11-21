package com.exercise1.domain.database.treedebris

import com.exercise1.domain.database.shared.Activity
import com.exercise1.domain.database.shared.Event
import com.exercise1.domain.database.shared.GeneralInfo
import com.exercise1.domain.database.shared.TreeLocation
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "tree_debris")
data class TreeDebris(
  @Id
  @Column(name = "event_id")
  var id: Long?,

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_tree_location")
  val location: TreeLocation? = null,

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
