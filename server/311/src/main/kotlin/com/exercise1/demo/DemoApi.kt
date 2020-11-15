package com.exercise1.demo

import com.exercise1.security.authentication.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/demo")
class DemoApi {
  @PostMapping
  @PreAuthorize("hasRole('GUEST')")
  fun demo(@RequestBody user: UserDto): ResponseEntity<Any> {
    return ResponseEntity.ok("hello")
  }
}
