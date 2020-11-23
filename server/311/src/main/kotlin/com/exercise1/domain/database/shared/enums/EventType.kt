package com.exercise1.domain.database.shared.enums

enum class EventType(val presentation: String) {
  ABANDONED_VEHICLES(presentation = "abandonedvehiclecomplaint"),
  ALLEY_LIGHT_OUT(presentation = "alleylightout"),
  GARBAGE_CART(presentation = "garbagecartblackmaintenance/replacement"),
  GRAFFITI_REMOVAL(presentation = "graffitiremoval"),
  POTHOLE_IN_STREET(presentation = "potholeinstreet"),
  RODENT_BAITING(presentation = "rodentbaiting/ratcomplaint"),
  SANITATION_VIOLATION(presentation = "sanitationcodeviolation"),
  STREET_LIGHTS_ALL_OUT(presentation = "streetlights-all/out"),
  STREET_LIGHT_OUT(presentation = "streetlightout"),
  TREE_DEBRIS(presentation = "treedebris"),
  TREE_TRIM(presentation = "treetrim");

  companion object {
    fun from(presentation: String): EventType =
      when(presentation) {
        ABANDONED_VEHICLES.presentation -> ABANDONED_VEHICLES
        ALLEY_LIGHT_OUT.presentation -> ALLEY_LIGHT_OUT
        GARBAGE_CART.presentation -> GARBAGE_CART
        GRAFFITI_REMOVAL.presentation -> GRAFFITI_REMOVAL
        POTHOLE_IN_STREET.presentation -> POTHOLE_IN_STREET
        RODENT_BAITING.presentation -> RODENT_BAITING
        SANITATION_VIOLATION.presentation -> SANITATION_VIOLATION
        STREET_LIGHTS_ALL_OUT.presentation -> STREET_LIGHTS_ALL_OUT
        STREET_LIGHT_OUT.presentation -> STREET_LIGHT_OUT
        "streetlight-1/out" -> STREET_LIGHT_OUT
        TREE_DEBRIS.presentation -> TREE_DEBRIS
        TREE_TRIM.presentation -> TREE_TRIM
        else -> throw NoSuchElementException("Key $presentation does not exists")
      }
  }
}
