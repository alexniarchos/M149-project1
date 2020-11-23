package com.exercise1.insert.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface InsertApi {
  fun insert(@RequestBody insertInputDto: InsertInputDto): ResponseEntity<Any?>
}
