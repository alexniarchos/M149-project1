package com.exercise1.select.queries

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface ZipCodeTypeTimesRepository: JpaRepository<ZipCodeTypeTimes, Long> {
  @Query(value = """
    select *
    from (
       select zip_code, type, count(*) times, row_number() over (partition by zip_code order by count(*) desc ) rn
       from incidents.event
       where cast(creation_date as date) = cast(:day as date)
       group by zip_code, type
       order by times desc
     ) a
    where a.rn = 1;
  """, nativeQuery = true)
  fun findMostCommonServiceRequestPerZipCode(day: Instant): List<ZipCodeTypeTimes>?
}
