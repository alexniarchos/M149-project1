package com.exercise1.domain.database.alleylightsout

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlleyLightsOutRepository: JpaRepository<AlleyLightsOut, Long>
