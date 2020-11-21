package com.exercise1.domain.database.treetrims

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TreeTrimsRepository: JpaRepository<TreeTrims, Long>
