package com.exercise1.domain.database.dump

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "dump")
@SequenceGenerator(name = "event_seq", sequenceName = "event_seq", allocationSize = 1)
data class Dump(
  @Id
  @Column(name = "event_id")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
  val eventId: Long?,

  @Column(name = "creation_date")
  val creationDate: String?,

  @Column(name = "status")
  val status: String?,

  @Column(name = "completion_date")
  val completionDate: String?,

  @Column(name = "service_request_number")
  val serviceRequestNumber: String?,

  @Column(name = "type")
  val type: String?,

  @Column(name = "address")
  val address: String?,

  @Column(name = "zip_code")
  val zipCode: String?,

  @Column(name = "x_coordinate")
  val xCoordinate: String?,

  @Column(name = "y_coordinate")
  val yCoordinate: String?,

  @Column(name = "ward")
  val ward: String?,

  @Column(name = "police_district")
  val policeDistrict: String?,

  @Column(name = "community_area")
  val communityArea: String?,

  @Column(name = "latitude")
  val latitude: String?,

  @Column(name = "longitude")
  val longitude: String?,

  @Column(name = "historical_wards")
  val historicalWards: String?,

  @Column(name = "zip_codes")
  val zipCodes: String?,

  @Column(name = "community_areas")
  val communityAreas: String?,

  @Column(name = "cencus_tracts")
  val cencusTracts: String?,

  @Column(name = "wards")
  val wards: String?,

  @Column(name = "ssa")
  val ssa: String?,

  @Column(name = "licence_plate", length = 512)
  val licencePlate: String?,

  @Column(name = "model")
  val model: String?,

  @Column(name = "color")
  val color: String?,

  @Column(name = "days_stationed")
  val daysStationed: String?,

  @Column(name = "number_delivered")
  val numberDelivered: String?,

  @Column(name = "surface_type")
  val typeOfSurface: String?,

  @Column(name = "location")
  val location: String?,

  @Column(name = "number_filled")
  val numberFilled: String?,

  @Column(name = "number_of_premises_baited")
  val numberOfPremisesBaited: String?,

  @Column(name = "number_of_premises_with_garbage")
  val numberOfPremisesWithGarbage: String?,

  @Column(name = "number_of_premises_with_rats")
  val numberOfPremisesWithRats: String?,

  @Column(name = "type_of_violation")
  val typeOfViolation: String?,

  @Column(name = "current_activity")
  val currentActivity: String?,

  @Column(name = "most_recent")
  val mostRecentAction: String?
)
