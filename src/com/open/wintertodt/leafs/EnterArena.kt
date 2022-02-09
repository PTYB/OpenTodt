package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants.ACTION_ENTER
import com.open.wintertodt.OpenWintertodtConstants.AREA_INSIDE_ARENA
import com.open.wintertodt.OpenWintertodtConstants.OBJECT_DOOR
import com.open.wintertodt.OpenWintertodtConstants.TILE_NEAR_DOOR_OUTSIDE
import com.open.wintertodt.Script
import org.powbot.api.Condition
import org.powbot.api.Tile
import org.powbot.api.rt4.*
import org.powbot.api.rt4.walking.local.LocalPathFinder
import org.powbot.api.script.tree.Leaf

class EnterArena(script: Script) : Leaf<Script>(script, "Entering arena") {
    override fun execute() {
        if (Bank.opened()) {
            Bank.close()
            return
        }

        val door = Objects.stream(20, GameObject.Type.INTERACTIVE).name(OBJECT_DOOR)
            .first()

        if (door == GameObject.Nil) {
            Movement.builder(TILE_NEAR_DOOR_OUTSIDE)
                .setWalkUntil { TILE_NEAR_DOOR_OUTSIDE.distance() < 2 }
                .setRunMin(50)
                .setRunMax(70)
                .move()
        } else {
            walkToDoor(door)
        }
    }

    private fun walkToDoor(door: GameObject) {
        if (!door.inViewport() || door.tile.distance() >= 3) {
            val targetTile = Tile(door.tile.x, 3520)
            LocalPathFinder.findPath(targetTile)
                .traverseUntilReached(3.0)
        }

        if (door.inViewport() && door.interact(ACTION_ENTER)) {
            Condition.wait({ AREA_INSIDE_ARENA.contains(Players.local()) }, 1000, 10)
        }
    }
}