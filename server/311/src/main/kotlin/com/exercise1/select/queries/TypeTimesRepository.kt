package com.exercise1.select.queries

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.time.Instant

@Repository
interface TypeTimesRepository: JpaRepository<TypeTimes, String> {
  @Query(value = """
    select type, count(*) as times
    from incidents.event
    where creation_date < :maxRange
      and creation_date > :minRange
    group by type
    order by times desc;
  """, nativeQuery = true)
  fun findTotalRequestsPerType(minRange: Instant, maxRange: Instant): List<TypeTimes>?

  @Query(value = """
    select type, count(*) times
    from incidents.event
    where latitude < :maxLatitude
      and longitude < :maxLongitude
      and latitude > :minLatitude
      and longitude > :minLongitude
      and cast(creation_date as date) = cast(:day as date)
    group by type
    order by times desc
    limit 1;
  """, nativeQuery = true)
  fun findMostCommonServiceInPlace(
    minLatitude: BigDecimal,
    minLongitude: BigDecimal,
    maxLatitude: BigDecimal,
    maxLongitude: BigDecimal,
    day: Instant
  ): TypeTimes?
}
