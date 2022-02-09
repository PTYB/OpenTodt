package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants
import com.open.wintertodt.OpenWintertodtConstants.ACTION_ENTER
import com.open.wintertodt.OpenWintertodtConstants.AREA_INSIDE_ARENA
import com.open.wintertodt.OpenWintertodtConstants.TILE_NEAR_DOOR_INSIDE
import com.open.wintertodt.Script
import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.rt4.GameObject
import org.powbot.api.rt4.Objects
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.walking.local.LocalPathFinder
import org.powbot.api.script.tree.Leaf

class ExitArena(script: Script) : Leaf<Script>(script, "Exiting arena") {
    override fun execute() {
        val door = Objects.stream().type(GameObject.Type.INTERACTIVE).name(OpenWintertodtConstants.OBJECT_DOOR)
            .first()

        if (!door.inViewport() || door.tile.distance() >= 3) {
            LocalPathFinder.findPath(TILE_NEAR_DOOR_INSIDE.derive(Random.nextInt(-2, 2), 0))
                .traverseUntilReached(3.0)
        }

        if (door.inViewport() && door.interact(ACTION_ENTER)) {
            Condition.wait({ !AREA_INSIDE_ARENA.contains(Players.local()) }, 1000, 10)
        }
    }
}