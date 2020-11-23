package com.exercise1.domain.queries.select

import com.exercise1.domain.database.shared.enums.EventType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface DateTimesRepository: JpaRepository<DateTimes, String> {
  @Query(value = """
    select cast(creation_date as date) as date, count(*) times
    from incidents.event
    where creation_date < :maxRange
      and creation_date > :minRange
      and type = :type
    group by date
    order by date desc;
  """, nativeQuery = true)
  fun findTotalRequestsPerDayForType(maxRange: Instant, minRange: Instant, type: EventType): List<DateTimes>
}
