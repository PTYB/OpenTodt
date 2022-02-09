package com.open.wintertodt.leafs

import com.open.wintertodt.OpenWintertodtConstants
import com.open.wintertodt.Script
import org.powbot.api.Notifications
import org.powbot.api.rt4.Bank
import org.powbot.api.rt4.Inventory
import org.powbot.api.script.tree.Leaf
import org.powbot.mobile.script.ScriptManager

class Banking(script: Script) : Leaf<Script>(script, "Banking") {
    override fun execute() {
        val bankItem = Bank.stream().name(script.configuration.foodName).first()
        val inventoryCount = Inventory.stream().name(script.configuration.foodName).count(true).toInt()
        if (bankItem.stackSize() < script.configuration.foodCount - inventoryCount) {
            Notifications.showNotification("Stopping script because out of food")
            ScriptManager.stop()
            return
        }

        if (Bank.depositAllExcept(
                OpenWintertodtConstants.TOOL_KNIFE,
                OpenWintertodtConstants.TOOL_TINBERBOX,
                OpenWintertodtConstants.TOOL_HAMMER,
                OpenWintertodtConstants.ITEM_BRONZE_AXE,
                OpenWintertodtConstants.ITEM_IRON_AXE,
                OpenWintertodtConstants.ITEM_STEEL_AXE,
                OpenWintertodtConstants.ITEM_MITHRIL_AXE,
                OpenWintertodtConstants.ITEM_ADAMANT_AXE,
                OpenWintertodtConstants.ITEM_RUNE_AXE,
                OpenWintertodtConstants.ITEM_DRAGON_AXE,
                script.configuration.foodName
            )
        ) {
            Bank.withdraw(script.configuration.foodName, (script.configuration.foodCount - inventoryCount))
        }
    }
}