package com.exercise1.domain.database.sanitationviolation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SanitationViolationRepository: JpaRepository<SanitationViolation, Long>
