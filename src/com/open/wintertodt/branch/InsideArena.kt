package com.open.wintertodt.branch

import com.open.wintertodt.OpenWintertodtConstants.AREA_INSIDE_ARENA
import com.open.wintertodt.Script
import com.open.wintertodt.helpers.FoodHelper
import com.open.wintertodt.leafs.ExitArena
import com.open.wintertodt.leafs.LightInitialFire
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class IsInside(script: Script) : Branch<Script>(script, "Is inside arena") {
    override val successComponent: TreeComponent<Script> = IsGameRunning(script)
    override val failedComponent: TreeComponent<Script> = HasFood(script)

    override fun validate(): Boolean {
        return AREA_INSIDE_ARENA.contains(Players.local())
    }
}

class HasMinimumFood(script: Script) : Branch<Script>(script, "Has minimum food") {
    override val successComponent: TreeComponent<Script> = LightInitialFire(script)
    override val failedComponent: TreeComponent<Script> = ExitArena(script)

    override fun validate(): Boolean {
        return Inventory.stream().name(*FoodHelper.getFoodInformation(script.configuration.foodName)).count(true) >= script.configuration.minFoodCount
    }
}