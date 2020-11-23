package com.exercise1.select.queries

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ColorTimesRepository: JpaRepository<ColorTimes, String> {
  @Query(value = """
    select color, count(*) times
    from incidents.abandoned_vehicles
    where color is not null
    group by color
    order by times desc
    limit 1 offset 1;
  """, nativeQuery = true)
  fun findSecondMostCommonColorOfAbandonedVehicles(): ColorTimes?
}
