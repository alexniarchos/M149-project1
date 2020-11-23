package com.exercise1.useraction

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserActionRepository: JpaRepository<UserAction, Long>
