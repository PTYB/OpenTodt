package com.open.wintertodt.leafs

import com.open.wintertodt.Script
import com.open.wintertodt.helpers.FoodHelper
import org.powbot.api.Condition
import org.powbot.api.rt4.Inventory
import org.powbot.api.rt4.Item
import org.powbot.api.rt4.Players
import org.powbot.api.script.tree.Leaf

class EatFood(script: Script) : Leaf<Script>(script, "Eating food") {
    override fun execute() {
        val firstFood = Inventory.stream().name(*FoodHelper.getFoodInformation(script.configuration.foodName))
            .first()

        if (firstFood != Item.Nil) {
            val action = if (firstFood.actions().contains("Eat")) "Eat" else "Drink"
            if (firstFood.interact(action) && Condition.wait({ Players.local().animation() != -1 }, 500, 3)) {
                Condition.wait { !firstFood.valid() }
            }
        }
    }
}