package com.exercise1.domain.database.sanitationviolation

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
@Table(name = "sanitation_violation")
data class SanitationViolation(
  @Id
  @Column(name = "event_id")
  var id: Long?,

  @Column(name = "type_of_violation")
  val typeOfViolation: String?,

  @OneToOne
  @JoinColumn(name = "event_id")
  val generalInfo: GeneralInfo? = null,

  @OneToOne
  @JoinColumn(name = "event_id")
  val event: Event? = null
)

