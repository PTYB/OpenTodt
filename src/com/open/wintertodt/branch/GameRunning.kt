package com.open.wintertodt.branch

import com.open.wintertodt.OpenWintertodtConstants
import com.open.wintertodt.OpenWintertodtConstants.AREA_NEAR_DOOR
import com.open.wintertodt.OpenWintertodtConstants.ITEM_BRUMA_KINDLING
import com.open.wintertodt.OpenWintertodtConstants.ITEM_BRUMA_ROOT
import com.open.wintertodt.Script
import com.open.wintertodt.extensions.count
import com.open.wintertodt.helpers.CommonMethods.isBrazierAlive
import com.open.wintertodt.helpers.CommonMethods.isPyromancerDead
import com.open.wintertodt.helpers.CommonMethods.remainingHealthPercentage
import com.open.wintertodt.helpers.FoodHelper
import com.open.wintertodt.helpers.PointHelper.getPointsContributed
import com.open.wintertodt.leafs.*
import org.powbot.api.rt4.*
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent
import java.util.logging.Logger

// TODO Drop vials and jugs
class IsGameRunning(script: Script) : Branch<Script>(script, "Is game running") {
    override val successComponent: TreeComponent<Script> = ShouldIdleAtFiveHundred(script)
    override val failedComponent: TreeComponent<Script> = HasMinimumFood(script)

    override fun validate(): Boolean {
        return Varpbits.varpbit(OpenWintertodtConstants.VARPBIT_RESPAWN) == 0
    }
}

class ShouldIdleAtFiveHundred(script: Script) : Branch<Script>(script, "Should idle at 500") {
    override val successComponent: TreeComponent<Script> = IdleInSafezone(script)
    override val failedComponent: TreeComponent<Script> = NeedsToEat(script)

    override fun validate(): Boolean {
        return script.configuration.idleAtFiveHundred && getPointsContributed() >= 500
    }
}

class NeedsToEat(script: Script) : Branch<Script>(script, "Needs to eat") {
    override val successComponent: TreeComponent<Script> = HasFoodToEat(script)
    override val failedComponent: TreeComponent<Script> = ShouldStayInSafeZone(script)

    override fun validate(): Boolean {
        return FoodHelper.needToEat(script)
    }
}

class ShouldStayInSafeZone(script: Script) : Branch<Script>(script, "Is in safezone") {
    override val successComponent: TreeComponent<Script> = IdleInSafezone(script)
    override val failedComponent: TreeComponent<Script> = ShouldStartLightingInventory(script)

    override fun validate(): Boolean {
        return AREA_NEAR_DOOR.contains(Players.local()) && Inventory.stream()
            .name(*FoodHelper.getFoodInformation(script.configuration.foodName))
            .count() <= 0
    }
}

class ShouldStartLightingInventory(script: Script) : Branch<Script>(script, "Lighting stuff") {

    private var logger: Logger = Logger.getLogger(this.javaClass.simpleName)

    override val successComponent: TreeComponent<Script> = CanLightFire(script)
    override val failedComponent: TreeComponent<Script> = ShouldWalkToSafespot(script)

    // TODO Figure out how to split it better between different configurations without duplicate counts
    override fun validate(): Boolean {
        // If its fully crafted and fletching enabled
        val kindlingCount = Inventory.count(ITEM_BRUMA_KINDLING)
        val rootCount = Inventory.count(ITEM_BRUMA_ROOT)
        val inventoryFull = Inventory.isFull()
        var result = false
        if (script.status.lighting && kindlingCount + rootCount > 0) {
            result = true
            logger.info("Lighting because has remaining")
        } else if (inventoryFull && script.configuration.logsOnly) {
            result = true
            logger.info("Lighting because only logs")
        } else if (inventoryFull && rootCount == 0) {
            result = true
            logger.info("Lighting full kindling")
        } else if (rootCount == 0 && kindlingCount > 0) {
            result = true
            logger.info("Lighting since no logs to fletch")
        } else if (rootCount + kindlingCount >= remainingHealthPercentage()) {
            logger.info("Lighting because $rootCount, $kindlingCount, ${remainingHealthPercentage()}%")
            result = true
        }

        return result.also { script.status.lighting = it }
    }
}

class CanLightFire(script: Script) : Branch<Script>(script, "Can light fire") {

    override val successComponent: TreeComponent<Script> = LightingBrazier(script)
    override val failedComponent: TreeComponent<Script> = UpdateLocation(script)

    override fun validate(): Boolean {
        return isBrazierAlive(script.status.currentLocation) || !isPyromancerDead(script.status.currentLocation)
    }

}

class ShouldWalkToSafespot(script: Script) : Branch<Script>(script, "Should walk to safespot") {
    override val successComponent: TreeComponent<Script> = WalkToSafespot(script)
    override val failedComponent: TreeComponent<Script> = ShouldChopVines(script)

    override fun validate(): Boolean {
        return script.configuration.snowfallSafespot &&
                Players.local().tile() != script.status.currentLocation.safespotTile
    }
}

class ShouldChopVines(script: Script) : Branch<Script>(script, "Should chop vines") {
    override val successComponent: TreeComponent<Script> = ChoppingVines(script)
    override val failedComponent: TreeComponent<Script> = FletchLogs(script)

    override fun validate(): Boolean {
        return !Inventory.isFull() && Inventory.count(ITEM_BRUMA_KINDLING) == 0
    }
}

class HasFoodToEat(script: Script) : Branch<Script>(script, "Has food to eat") {
    override val successComponent: TreeComponent<Script> = EatFood(script)
    override val failedComponent: TreeComponent<Script> = IdleInSafezone(script)

    override fun validate(): Boolean {
        return Inventory.count(*FoodHelper.getFoodInformation(script.configuration.foodName)) > 0
    }
}