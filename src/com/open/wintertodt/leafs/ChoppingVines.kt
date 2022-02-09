package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants.ACTION_CHOP
import com.open.wintertodt.OpenWintertodtConstants.OBJECT_BRUMA_ROOT
import com.open.wintertodt.Script
import com.open.wintertodt.helpers.FoodHelper.needToEat
import org.powbot.api.Condition
import org.powbot.api.rt4.*
import org.powbot.api.rt4.walking.local.LocalPathFinder
import org.powbot.api.script.tree.Leaf

class ChoppingVines(script: Script) : Leaf<Script>(script, "Chopping logs") {
    override fun execute() {
        if (Players.local().animation() != -1) {
            return
        }

        val vine = getVines()

        if (vine == GameObject.Nil) {
            return
        }

        if (vine.inViewport()) {
            if (vine.interact(ACTION_CHOP)) {
                Condition.wait { Players.local().animation() != -1 || needToEat(script) }
            }

        } else {
            walkToObject(vine)
        }
    }

    private fun getVines(): GameObject {
        return Objects.stream().name(OBJECT_BRUMA_ROOT)
            .at(script.status.currentLocation.rootsTile).nearest().first()
    }

    private fun walkToObject(go: GameObject) {
        val startingHp = Combat.health()
        val targetTile = go.getWalkableNeighbours(false)
        if (targetTile.isEmpty()) {
            return
        }
        LocalPathFinder.findPath(targetTile.random())
            .traverseUntilReached(3.0) { Combat.health() < startingHp }
    }

}