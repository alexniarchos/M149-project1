package com.exercise1.domain.database.abandonedvehicles

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AbandonedVehiclesRepository: JpaRepository<AbandonedVehicles, Long>
