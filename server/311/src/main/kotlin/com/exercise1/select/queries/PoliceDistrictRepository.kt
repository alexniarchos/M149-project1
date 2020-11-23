package com.exercise1.select.queries

import com.exercise1.domain.database.shared.Event
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.time.Instant

@Repository
interface PoliceDistrictRepository: JpaRepository<Event, Long> {
  @Query("""
    select distinct a.pd as police_district
    from (
           select distinct police_district pd
           from incidents.event
                    inner join incidents.pot_holes on event.event_id = pot_holes.event_id
           where cast(creation_date as date) = cast(:day as date)
             and pot_holes.number_filled > 1
             and completion_date is not null
         ) a,
         (
           select distinct police_district pd
           from incidents.event
                    inner join incidents.rodent_baiting on event.event_id = rodent_baiting.event_id
           where cast(creation_date as date) = cast(:day as date)
             and rodent_baiting.number_of_premises_baited > 1
             and completion_date is not null
         ) b
    where a.pd = b.pd
  """, nativeQuery = true)
  fun findPoliceDistrictPotHolesRodentBaiting(day: Instant): List<PoliceDistrict>?
}
