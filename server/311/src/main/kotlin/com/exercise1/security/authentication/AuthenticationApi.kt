package com.exercise1.security.authentication

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface AuthenticationApi {
  fun authenticateUser(@RequestBody user: UserDto): ResponseEntity<Any>
  fun createUser(@RequestBody user: UserDto): ResponseEntity<Any>
}
