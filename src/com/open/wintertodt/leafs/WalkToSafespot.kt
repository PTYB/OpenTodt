package com.open.wintertodt.leafs

import com.open.wintertodt.Script
import com.open.wintertodt.models.WintertodtLocation
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class WalkToSafespot(script: Script) : Leaf<Script>(script, "Walking to safespot") {
    private val currentLocation: WintertodtLocation get() = script.status.currentLocation

    override fun execute() {
        Movement.builder(currentLocation.safespotTile)
            .setWalkUntil { Players.local().tile() == currentLocation.safespotTile }
            .move()
    }
}