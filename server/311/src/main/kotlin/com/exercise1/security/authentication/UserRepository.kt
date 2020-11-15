package com.exercise1.security.authentication

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
  fun findByUsername(username: String): User?
}
