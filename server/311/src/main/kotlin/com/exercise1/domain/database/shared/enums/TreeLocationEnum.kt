package com.exercise1.domain.database.shared.enums

enum class TreeLocationEnum(val presentation: String) {
  ALLEY(presentation = "alley"),
  PARKWAY(presentation = "parkway"),
  VACANT_LOT("vacantlot");

  companion object {
    fun from(presentation: String): TreeLocationEnum =
      when (presentation) {
        ALLEY.presentation -> ALLEY
        PARKWAY.presentation -> PARKWAY
        VACANT_LOT.presentation -> VACANT_LOT
        else -> throw NoSuchElementException("Key $presentation does not exists")
      }
  }
}
