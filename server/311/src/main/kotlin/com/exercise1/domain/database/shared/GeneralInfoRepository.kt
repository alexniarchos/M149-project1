package com.exercise1.domain.database.shared

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GeneralInfoRepository: JpaRepository<GeneralInfo, Long>
