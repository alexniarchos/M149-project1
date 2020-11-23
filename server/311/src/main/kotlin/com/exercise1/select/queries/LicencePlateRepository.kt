package com.exercise1.domain.queries.select

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface LicencePlateRepository: JpaRepository<LicencePlate, String> {
  @Query(value = """
    select distinct licence_plate as licencePlate
    from incidents.abandoned_vehicles
    where licence_plate ~ '^[a-zA-Z0-9 ]*${'$'}'
    group by licence_plate
    having count(licence_plate) > 1;
  """, nativeQuery = true)
  fun findLicencePlatesAbandonedMultipleTimes(): List<LicencePlate>
}
