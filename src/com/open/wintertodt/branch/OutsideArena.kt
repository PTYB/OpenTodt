package com.open.wintertodt.branch

import com.open.wintertodt.Script
import com.open.wintertodt.leafs.Banking
import com.open.wintertodt.leafs.EnterArena
import com.open.wintertodt.leafs.OpenBank
import org.powbot.api.rt4.Bank
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
    override val failedComponent: TreeComponent<Script> = OpenBank(script)

    override fun validate(): Boolean {
        return Bank.opened()
    }
}
