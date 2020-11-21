package com.exercise1.domain.database.garbagecarts

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GarbageCartsRepository: JpaRepository<GarbageCarts, Long>
