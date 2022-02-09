package com.open.wintertodt.leafs

import com.open.wintertodt.Script
import com.open.wintertodt.helpers.CommonMethods.isPyromancerDead
import com.open.wintertodt.models.WintertodtLocation
import org.powbot.api.Condition
import org.powbot.api.rt4.Combat
import org.powbot.api.script.tree.Leaf
import java.util.logging.Logger

class UpdateLocation(script: Script) : Leaf<Script>(script, "Checking for location update") {
    private var logger: Logger = Logger.getLogger(this.javaClass.simpleName)

    override fun execute() {

        // Give leeway to revive
        val seStatus = isPyromancerDead(WintertodtLocation.SOUTH_EAST)
        val swStatus = isPyromancerDead(WintertodtLocation.SOUTH_WEST)
        val neStatus = isPyromancerDead(WintertodtLocation.NORTH_EAST)
        val nwStatus = isPyromancerDead(WintertodtLocation.NORTH_WEST)

        val hp = Combat.health()
        val revive = Condition.wait({ !isPyromancerDead(script.status.currentLocation) || Combat.health() < hp}, 1500, 2)

        if (revive) {
            logger.info("Pyromancer revived, not changing location.")
            return
        }

        logger.info("SW $swStatus., SE $seStatus, NW $nwStatus, NE $neStatus")
        if (isAtRegion(WintertodtLocation.SOUTH_EAST)) {
            script.status.currentLocation = if (!swStatus) {
                WintertodtLocation.SOUTH_WEST
            } else if (!neStatus) {
                WintertodtLocation.NORTH_EAST
            } else {
                WintertodtLocation.NORTH_WEST
            }
        } else if (isAtRegion(WintertodtLocation.SOUTH_WEST)) {
            script.status.currentLocation = if (!seStatus) {
                WintertodtLocation.SOUTH_EAST
            } else if (!nwStatus) {
                WintertodtLocation.NORTH_WEST
            } else {
                WintertodtLocation.NORTH_EAST
            }
        } else if (isAtRegion(WintertodtLocation.NORTH_WEST)) {
            script.status.currentLocation = if (!swStatus) {
                WintertodtLocation.SOUTH_WEST
            } else if (!seStatus) {
                WintertodtLocation.SOUTH_EAST
            } else {
                WintertodtLocation.NORTH_EAST
            }
        } else {
            script.status.currentLocation = if (!seStatus) {
                WintertodtLocation.SOUTH_EAST
            } else if (!swStatus) {
                WintertodtLocation.SOUTH_WEST
            } else {
                WintertodtLocation.NORTH_WEST
            }
        }

        logger.info("Updated location is ${script.status.currentLocation}")
    }

    private fun isAtRegion(location: WintertodtLocation): Boolean {
        return script.status.currentLocation == location
    }

}