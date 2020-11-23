package com.exercise1.select.queries

import com.exercise1.domain.database.shared.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EventIdNumberOfOccurrencesRepository: JpaRepository<Event, Long> {
  @Query(value = """
    select event_id, number_of_premises_baited as number
    from incidents.rodent_baiting
    where number_of_premises_baited < :numberOfPremises
    ;
  """, nativeQuery = true)
  fun findPremisesLesserThan(numberOfPremises: Long): List<EventIdNumberOfOccurrences>?

  @Query(value = """
    select event_id, number_of_premises_with_garbage as number
    from incidents.rodent_baiting
    where number_of_premises_with_garbage < :numberOfPremises
    ;
  """, nativeQuery = true)
  fun findPremisesWithGarbageLesserThan(numberOfPremises: Long): List<EventIdNumberOfOccurrences>?

  @Query(value = """
    select event_id, number_of_premises_with_rats as number
    from incidents.rodent_baiting
    where number_of_premises_with_rats < :numberOfPremises
    ;
  """, nativeQuery = true)
  fun findPremisesWithRatsLesserThan(numberOfPremises: Long): List<EventIdNumberOfOccurrences>?
}
