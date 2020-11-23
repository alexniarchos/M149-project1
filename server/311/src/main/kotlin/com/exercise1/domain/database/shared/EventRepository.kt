package com.exercise1.domain.database.shared

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository: JpaRepository<Event, Long> {
  fun findAllByAddressContains(address: String): List<Event>
  fun findAllByZipCode(zipCode: Int): List<Event>
}
