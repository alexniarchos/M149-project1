package com.exercise1.domain.database.shared.enums

enum class EventStatus(val presentation: String) {
  COMPLETED(presentation = "completed"),
  COMPLETED_DUPLICATE(presentation = "completed-dup"),
  OPEN(presentation = "open"),
  OPEN_DUPLICATE(presentation = "open-dup");

  companion object {
    fun from(presentation: String): EventStatus =
      when(presentation) {
        COMPLETED.presentation -> COMPLETED
        COMPLETED_DUPLICATE.presentation -> COMPLETED_DUPLICATE
        OPEN.presentation -> OPEN
        OPEN_DUPLICATE.presentation -> OPEN_DUPLICATE
        else -> throw NoSuchElementException("Key $presentation does not exists")
      }
  }
}
