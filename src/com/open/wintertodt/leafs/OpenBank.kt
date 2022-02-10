package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants.BANK_TODT
import com.open.wintertodt.Script
import com.open.wintertodt.extensions.nearestGameObject
import org.powbot.api.Condition
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Objects
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class OpenBank(script: Script) : Leaf<Script>(script, "Opening bank") {
    override fun execute() {
        val bankChest = Objects.nearestGameObject("Bank chest")

        if (bankChest.inViewport(true) && bankChest.interact("Bank")) {
            Condition.wait ({ Players.local().inMotion() || Bank.opened() }, 500, 5)
            Condition.wait({ !Players.local().inMotion() || Bank.opened() }, 500, 15)
        } else {
            Movement.builder(BANK_TODT)
                .setWalkUntil { BANK_TODT.matrix().inViewport(true)}
                .move()
        }
    }
}