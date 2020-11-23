package com.exercise1.insert.api

import com.exercise1.insert.service.InsertService
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin
@RestController
@RequestMapping("/insert")
class InsertApiImpl(
  private val insertService: InsertService
) : InsertApi {
  @PostMapping
  @PreAuthorize("hasRole('GUEST')")
  override fun insert(@RequestBody insertInputDto: InsertInputDto): ResponseEntity<Any?> {
    val result = insertService.route(insertInputDto)
    return result?.let { ResponseEntity.ok(result) } ?: ResponseEntity.ok().build()
  }
}
