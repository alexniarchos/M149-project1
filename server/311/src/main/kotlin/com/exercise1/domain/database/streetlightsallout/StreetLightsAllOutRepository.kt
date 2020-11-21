package com.exercise1.domain.database.streetlightsallout

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StreetLightsAllOutRepository: JpaRepository<StreetLightsAllOut, Long>
