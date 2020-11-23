package com.exercise1.useraction

import com.exercise1.insert.api.InsertInputDto
import com.exercise1.select.api.SelectInputDto

data class ActionData(
  val insertData: InsertInputDto? = null,
  val selectData: SelectInputDto? = null
)
