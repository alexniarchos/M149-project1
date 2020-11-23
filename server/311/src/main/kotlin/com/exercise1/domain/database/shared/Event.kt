package com.exercise1.domain.database.shared

import com.exercise1.domain.database.shared.enums.EventStatus
import com.exercise1.domain.database.shared.enums.EventType
import java.math.BigDecimal
import java.time.Instant
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "event")
@SequenceGenerator(name = "event_seq", sequenceName = "event_seq", allocationSize = 1)
data class Event(

  @Id
  @Column(name = "event_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
  var id: Long? = null,

  @Column(name = "creation_date")
  val creationDate: Instant,

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  val status: EventStatus,

  @Column(name = "completion_date")
  val completionDate: Instant?,

  @Column(name = "service_request_number")
  val serviceRequestNumber: String,

  @Enumerated(EnumType.STRING)
  @Column(name = "type")
  val type: EventType,

  @Column(name = "address")
  val address: String?,

  @Column(name = "zip_code")
  val zipCode: Int?,

  @Column(name = "x_coordinate", precision = 25, scale = 15)
  val xCoordinate: BigDecimal?,

  @Column(name = "y_coordinate", precision = 25, scale = 15)
  val yCoordinate: BigDecimal?,

  @Column(name = "ward")
  val ward: Long?,

  @Column(name = "police_district")
  val policeDistrict: Int?,

  @Column(name = "community_area")
  val communityArea: Int?,

  @Column(name = "latitude", precision = 25, scale = 15)
  val latitude: BigDecimal?,

  @Column(name = "longitude", precision = 25, scale = 15)
  val longitude: BigDecimal?
)
