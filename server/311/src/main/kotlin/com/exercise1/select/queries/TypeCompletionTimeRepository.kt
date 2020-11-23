package com.exercise1.select.queries

import com.exercise1.domain.database.shared.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface TypeCompletionTimeRepository: JpaRepository<Event, Long> {
  @Query(value = """
    select type, avg(completion_date - creation_date) as time
    from incidents.event
    where creation_date < :maxRange
      and creation_date > :minRange
      and completion_date is not null
    group by type;
  """, nativeQuery = true)
  fun findAverageCompletionTimePerDay(minRange: Instant, maxRange: Instant): List<TypeCompletionTime>?
}
