package com.exercise1.initdb

import com.exercise1.domain.database.shared.enums.EventType
import com.exercise1.initdb.abandonedvehicles.AbandonedVehiclesManipulator
import com.exercise1.initdb.alleylightsout.AlleyLightsOutManipulator
import com.exercise1.initdb.garbagecarts.GarbageCartsManipulator
import com.exercise1.initdb.graffitiremoval.GraffitiRemovalManipulator
import com.exercise1.initdb.potholes.PotHolesManipulator
import com.exercise1.initdb.rodentbaiting.RodentBaitingManipulator
import com.exercise1.initdb.sanitationvalidation.SanitationValidationManipulator
import com.exercise1.initdb.streetlightsallout.StreetLightsAllOutManipulator
import com.exercise1.initdb.streetlightsoneout.StreetLightsOneOutManipulator
import com.exercise1.initdb.treedebris.TreeDebrisManipulator
import com.exercise1.initdb.treetrims.TreeTrimsManipulator
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component

@Component
@ConditionalOnProperty(value = ["app.initdb.enabled"], matchIfMissing = false)
class InitializeDatabase(
  private val abandonedVehiclesManipulator: AbandonedVehiclesManipulator,
  private val alleyLightsOutManipulator: AlleyLightsOutManipulator,
  private val garbageCartsManipulator: GarbageCartsManipulator,
  private val graffitiRemovalManipulator: GraffitiRemovalManipulator,
  private val potHolesManipulator: PotHolesManipulator,
  private val rodentBaitingManipulator: RodentBaitingManipulator,
  private val sanitationValidationManipulator: SanitationValidationManipulator,
  private val streetLightsAllOutManipulator: StreetLightsAllOutManipulator,
  private val streetLightsOneOutManipulator: StreetLightsOneOutManipulator,
  private val treeDebrisManipulator: TreeDebrisManipulator,
  private val treeTrimsManipulator: TreeTrimsManipulator,
  @Value(value = "#{\${data.locations}}") val locations: Map<EventType, String>
) : CommandLineRunner {
  override fun run(vararg args: String?) {
    abandonedVehiclesManipulator.manipulate(locations[EventType.ABANDONED_VEHICLES] ?: "")
    alleyLightsOutManipulator.manipulate(locations[EventType.ALLEY_LIGHT_OUT] ?: "")
    garbageCartsManipulator.manipulate(locations[EventType.GARBAGE_CART] ?: "")
    graffitiRemovalManipulator.manipulate(locations[EventType.GRAFFITI_REMOVAL] ?: "")
    potHolesManipulator.manipulate(locations[EventType.POTHOLE_IN_STREET] ?: "")
    rodentBaitingManipulator.manipulate(locations[EventType.RODENT_BAITING] ?: "")
    sanitationValidationManipulator.manipulate(locations[EventType.SANITATION_VIOLATION] ?: "")
    streetLightsAllOutManipulator.manipulate(locations[EventType.STREET_LIGHTS_ALL_OUT] ?: "")
    streetLightsOneOutManipulator.manipulate(locations[EventType.STREET_LIGHT_OUT] ?: "")
    treeDebrisManipulator.manipulate(locations[EventType.TREE_DEBRIS] ?: "")
    treeTrimsManipulator.manipulate(locations[EventType.TREE_TRIM] ?: "")
  }
}
