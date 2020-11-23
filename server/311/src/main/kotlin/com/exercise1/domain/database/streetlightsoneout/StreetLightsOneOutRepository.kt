package com.exercise1.domain.database.streetlightsoneout

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StreetLightsOneOutRepository: JpaRepository<StreetLightsOneOut, Long>
