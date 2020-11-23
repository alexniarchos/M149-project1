package com.exercise1.select.api

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface SelectApi {
  fun select(@RequestBody selectInputDto: SelectInputDto): ResponseEntity<Any?>
}
