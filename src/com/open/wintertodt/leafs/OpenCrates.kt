package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants
import com.open.wintertodt.Script
import com.open.wintertodt.extensions.count
import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.Tile
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class OpenCrates(script: Script) : Leaf<Script>(script, "Opening crates") {
    override fun execute() {
        if (OpenWintertodtConstants.BANK_TODT.distanceTo(Players.local()) > 3 && Movement.destination() == Tile.Nil) {
            Movement.step(OpenWintertodtConstants.BANK_TODT, 1)
        }

        Inventory.stream().name(OpenWintertodtConstants.ITEM_CRATE).forEach {
            if (it.interact("Open")) {
                Condition.sleep(Random.nextInt(400, 700))
            }
        }

        Condition.wait({ Inventory.count(OpenWintertodtConstants.ITEM_CRATE) == 0 }, 5, 400)
    }
}