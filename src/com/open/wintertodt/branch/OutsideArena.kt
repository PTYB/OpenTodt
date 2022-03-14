package com.open.wintertodt.branch

import com.open.wintertodt.OpenWintertodtConstants.ITEMS_PYROMANCER
import com.open.wintertodt.OpenWintertodtConstants.ITEM_CRATE
import com.open.wintertodt.Script
import com.open.wintertodt.extensions.count
import com.open.wintertodt.leafs.*
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Equipment
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Branch
import org.powbot.api.script.tree.TreeComponent

class HasFood(script: Script) : Branch<Script>(script, "Has required food") {
    override val successComponent: TreeComponent<Script> = EnterArena(script)
    override val failedComponent: TreeComponent<Script> = IsBankOpened(script)

    override fun validate(): Boolean {
        return Inventory.stream().name(script.configuration.foodName).count(true) >= script.configuration.foodCount
    }
}

class IsBankOpened(script: Script) : Branch<Script>(script, "Is bank opened") {
    override val successComponent: TreeComponent<Script> = Banking(script)
    override val failedComponent: TreeComponent<Script> = ShouldOpenCrates(script)

    override fun validate(): Boolean {
        return Bank.opened()
    }
}

class ShouldOpenCrates(script: Script) : Branch<Script>(script, "Open crates?") {
    override val successComponent: TreeComponent<Script> = OpenCrates(script)
    override val failedComponent: TreeComponent<Script> = HasGearToEquip(script)

    override fun validate(): Boolean {
        return script.configuration.openCrates && Inventory.count(ITEM_CRATE) > 0
    }
}

class HasGearToEquip(script: Script) : Branch<Script>(script, "Has gear to equip?") {
    override val successComponent: TreeComponent<Script> = EquipGearIfUpgraded(script)
    override val failedComponent: TreeComponent<Script> = OpenBank(script)

    override fun validate(): Boolean {
        if (!script.configuration.upgradeGear) {
            return false
        }

        val equipment = Equipment.get()
        val pyroInventory = Inventory.stream().name(*ITEMS_PYROMANCER).toList()

        pyroInventory.forEach {
            if (!equipment.contains(it)) {
                return true
            }
        }
        return false
    }
}
