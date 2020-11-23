package com.exercise1.domain.queries.select

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface DateSSATimesRepository: JpaRepository<DateSSATimes, String> {
  @Query(value = """
    select *
    from (select cast(creation_date as date) date,
                 ssa.value as ssa,
                 count(*) times,
                 row_number() over (partition by cast(creation_date as date) order by count(*) desc ) rn
          from incidents.event
                   inner join incidents.ssa on ssa.event_id = event.event_id
          where creation_date < :maxRange
            and creation_date > :minRange
          group by date, ssa.value
          order by times desc) a
    where a.rn <= 5
    order by date, times desc;
  """, nativeQuery = true)
  fun findTopFiveSSAPerDay(minRange: Instant, maxRange: Instant): List<DateSSATimes>
}
