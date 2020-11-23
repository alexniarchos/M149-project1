package com.exercise1.domain.database.potholes

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PotHolesRepository: JpaRepository<PotHoles, Long>
