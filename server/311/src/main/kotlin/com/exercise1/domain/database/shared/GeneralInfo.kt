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
@Table(name = "general_info")
data class GeneralInfo(

  @Id
  @Column(name = "general_info_id")
  val id: Long,

  @Column(name = "historical_wards")
  val historicalWards: Int?,

  @Column(name = "zip_codes")
  val zipCodes: Int?,

  @Column(name = "community_areas")
  val communityAreas: Int?,

  @Column(name = "cencus_tracts")
  val cencusTracts: Int?,

  @Column(name = "wards")
  val wards: Int?,

  @OneToOne
  @JoinColumn(name = "event_id")
  val event: Event? = null
)
