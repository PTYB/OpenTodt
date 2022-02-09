package com.open.wintertodt.helpers

import com.open.wintertodt.Script
import org.powbot.api.rt4.Combat
import java.util.logging.Logger

object FoodHelper {
    fun getFoodInformation(foodName: String): Array<String> {
        if (foodName == "Cake") {
            return arrayOf("Slice of cake", "2/3 cake", "Cake")
        }
        return arrayOf(foodName)
    }

    fun needToEat(script: Script): Boolean {
        val healthRequired = (Combat.maxHealth() * (script.configuration.healthRestorePercent / 100.0)).toInt()
        return Combat.health() < healthRequired
    }

}