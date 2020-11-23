package com.exercise1.domain.queries.select

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventIdNumberOfOccurrencesRepository: JpaRepository<EventIdNumberOfOccurrences, Long> {
  @Query(value = """
    select event_id, number_of_premises_baited as numberOfOccurrences
    from incidents.rodent_baiting
    where number_of_premises_baited < :number;
  """, nativeQuery = true)
  fun findPremisesGreaterThan(number: Long): List<EventIdNumberOfOccurrences>

  @Query(value = """
    select event_id, number_of_premises_with_garbage as numberOfOccurrences
    from incidents.rodent_baiting
    where number_of_premises_with_garbage < :number;
  """, nativeQuery = true)
  fun findPremisesWithGarbageGreaterThan(number: Long): List<EventIdNumberOfOccurrences>

  @Query(value = """
    select event_id, number_of_premises_with_rats as numberOfOccurrences
    from incidents.rodent_baiting
    where number_of_premises_with_rats < :number;
  """, nativeQuery = true)
  fun findPremisesWithRatsGreaterThan(number: Long): List<EventIdNumberOfOccurrences>
}
