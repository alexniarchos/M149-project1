package com.exercise1.domain.database.treedebris

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TreeDebrisRepository: JpaRepository<TreeDebris, Long>
