package com.open.wintertodt.helpers

import com.open.wintertodt.Script
import org.powbot.api.rt4.Combat

object FoodHelper {
    fun getFoodInformation(foodName: String): Array<String> {
        if (foodName == "Cake") {
            return arrayOf("Slice of cake", "2/3 cake", "Cake")
        }
        if (foodName.contains("Saradomin brew")) {
            return arrayOf("Saradomin brew(4)", "Saradomin brew(3)", "Saradomin brew(2)", "Saradomin brew(1)")
        }
        return arrayOf(foodName)
    }

    fun needToEat(script: Script): Boolean {
        val healthRequired = (Combat.maxHealth() * (script.configuration.healthRestorePercent / 100.0)).toInt()
        return Combat.health() < healthRequired
    }

}