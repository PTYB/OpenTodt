package com.open.wintertodt.helpers

import org.powbot.api.Condition
import org.powbot.api.Random
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Item

object InteractionsHelper {

    fun useItemOn(firstItem: Item, secondItem: Item): Boolean {
        if (!Inventory.opened()) {
            Inventory.open()
        }

        val selectedItem = Inventory.selectedItem()
        if (selectedItem != Item.Nil && selectedItem != firstItem) {
            !selectedItem.interact("Cancel")
            return false
        }

        if (!firstItem.interact("Use")) {
            return false
        } else {
            Condition.sleep(Random.nextInt(250, 450))
        }
        return secondItem.click()
    }
}