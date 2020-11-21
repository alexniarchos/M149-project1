package com.exercise1.domain.database.graffitiremoval

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GraffitiRemovalRepository: JpaRepository<GraffitiRemoval, Long>
