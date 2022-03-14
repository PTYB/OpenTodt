package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants
import com.open.wintertodt.Script
import org.powbot.api.Condition
import org.powbot.api.rt4.Equipment
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Leaf
import java.util.logging.Logger

class EquipGearIfUpgraded(script: Script) : Leaf<Script>(script, "Upgrading gear") {
    private val logger = Logger.getLogger(this.javaClass.name)

    override fun execute() {
        val equipment = Equipment.get()
        val pyroInventory = Inventory.stream().name(*OpenWintertodtConstants.ITEMS_PYROMANCER).toList()

        pyroInventory.forEach {
            if (!equipment.contains(it)) {
                logger.info("Attempting to equip $equipment")
                if (it.interact("Wear")) {
                    Condition.wait { !it.valid() }
                }
            }
        }
    }
}