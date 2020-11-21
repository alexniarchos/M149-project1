package com.exercise1.domain.database.dump

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DumpRepository: JpaRepository<Dump, Long>
