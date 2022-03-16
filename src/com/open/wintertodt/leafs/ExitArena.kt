package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants
import com.open.wintertodt.OpenWintertodtConstants.ACTION_ENTER
import com.open.wintertodt.OpenWintertodtConstants.AREA_INSIDE_ARENA
import com.open.wintertodt.OpenWintertodtConstants.TILE_NEAR_DOOR_INSIDE
import com.open.wintertodt.Script
import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.rt4.GameObject
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Objects
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.walking.local.LocalPathFinder
import org.powbot.api.script.tree.Leaf

class ExitArena(script: Script) : Leaf<Script>(script, "Exiting arena") {
    override fun execute() {
        val door = Objects.stream().type(GameObject.Type.INTERACTIVE).name(OpenWintertodtConstants.OBJECT_DOOR)
            .first()

        if (!door.inViewport() || door.tile.distance() >= 3) {
            val randomTile = TILE_NEAR_DOOR_INSIDE.derive(Random.nextInt(-2, 2), 0)
            if (randomTile.loaded()) {
                LocalPathFinder.findPath(randomTile)
                    .traverseUntilReached(3.0)
            } else {
                Movement.builder(randomTile)
                    .setRunMin(5)
                    .setRunMax(20)
                    .move()
            }
        }

        if (door.inViewport() && door.interact(ACTION_ENTER)) {
            val outside = Condition.wait({ !AREA_INSIDE_ARENA.contains(Players.local()) }, 1000, 10)
            if (outside){
                // Random sleep outside since it gives a black fade which prevents anything being done.
                Condition.sleep(Random.nextInt(1500, 3000))
            }
        }
    }
}