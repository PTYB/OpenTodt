package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants.ACTION_LIGHT
import com.open.wintertodt.OpenWintertodtConstants.OBJECT_BRAZIER
import com.open.wintertodt.OpenWintertodtConstants.OBJECT_BURNING_BRAZIER
import com.open.wintertodt.OpenWintertodtConstants.VARPBIT_RESPAWN
import com.open.wintertodt.Script
import com.open.wintertodt.extensions.Conditions
import com.open.wintertodt.models.WintertodtLocation
import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Leaf

class LightInitialFire(script: Script) : Leaf<Script>(script, "Light initial fire") {
    private val currentLocation: WintertodtLocation get() = script.status.currentLocation

    override fun execute() {
        val brazierTile = currentLocation.brazierTile
        if (Players.local().y() != currentLocation.brazierY || brazierTile.distanceTo(Players.local()) >= 2.5) {
            val xPos = Random.nextInt(currentLocation.minBrazierX, currentLocation.maxBrazierX)
            val tile = Tile(xPos, currentLocation.brazierY)
            Movement.builder(tile).setWalkUntil { Players.local().y() == currentLocation.brazierY }.move()
        }

        if (brazierTile.distanceTo(Players.local()) <= 2.5 && shouldLightInitialFire()) {

            val brazier = getBrazier()
            if (brazier.interact(ACTION_LIGHT, Game.singleTapEnabled())) {
                Condition.wait(Conditions.expGained(Constants.SKILLS_FIREMAKING))
            }
        }
    }

    private fun shouldLightInitialFire(): Boolean {
        val ticksValue = Varpbits.varpbit(VARPBIT_RESPAWN)
        return ticksValue <= 32768
    }

    private fun getBrazier(): GameObject {
        return Objects.stream(currentLocation.brazierTile, 4, GameObject.Type.INTERACTIVE)
            .name(OBJECT_BURNING_BRAZIER, OBJECT_BRAZIER)
            .nearest()
            .first()
    }
}