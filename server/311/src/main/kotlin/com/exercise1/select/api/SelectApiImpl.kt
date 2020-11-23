package com.exercise1.select.api

import com.exercise1.select.service.SelectService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/select")
class SelectApiImpl(
  private val selectService: SelectService
): SelectApi {

  @PostMapping("")
  @PreAuthorize("hasRole('GUEST')")
  override fun select(selectInputDto: SelectInputDto): ResponseEntity<Any?> {
    val response = selectService.route(selectInputDto)
    return response?.let { ResponseEntity.ok(response) } ?: ResponseEntity.ok().build()
  }
}
