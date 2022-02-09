package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants
import com.open.wintertodt.OpenWintertodtConstants.AREA_NEAR_DOOR
import com.open.wintertodt.Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Movement
import org.powbot.api.rt4.Players
import org.powbot.api.rt4.Varpbits
import org.powbot.api.script.tree.Leaf

class IdleInSafezone(script: Script) : Leaf<Script>(script, "Idling in safe zone") {
    override fun execute() {

        if (AREA_NEAR_DOOR.contains(Players.local())) {
            Condition.wait { Varpbits.varpbit(OpenWintertodtConstants.VARPBIT_RESPAWN) != 0 }
        } else {
            Movement.builder(AREA_NEAR_DOOR.randomTile)
                .setWalkUntil { AREA_NEAR_DOOR.contains(Players.local()) ||
                        Varpbits.varpbit(OpenWintertodtConstants.VARPBIT_RESPAWN) != 0 }
                .move()
        }
    }
}