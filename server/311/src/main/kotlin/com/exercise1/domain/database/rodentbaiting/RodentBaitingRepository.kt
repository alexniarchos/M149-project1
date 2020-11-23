package com.exercise1.domain.database.rodentbaiting

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RodentBaitingRepository: JpaRepository<RodentBaiting, Long>
