package com.open.wintertodt.helpers

import com.open.wintertodt.OpenWintertodtConstants
import com.open.wintertodt.OpenWintertodtConstants.TEXTURE_BURNING
import com.open.wintertodt.OpenWintertodtConstants.TEXTURE_PYROMANCERDEAD
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_ROOT_ID
import com.open.wintertodt.models.WintertodtLocation
import org.powbot.api.rt4.Component
import org.powbot.api.rt4.Widgets

object CommonMethods {
    private val energyPercentage = Regex(": (.*?)%")

    fun isPyromancerDead(location: WintertodtLocation): Boolean {
        return Widgets.component(WIDGETS_ROOT_ID, location.pyroComponentId).textureId() == TEXTURE_PYROMANCERDEAD
    }


    fun isBrazierAlive(location: WintertodtLocation): Boolean {
        return Widgets.component(WIDGETS_ROOT_ID, location.brazierComponentId).textureId() == TEXTURE_BURNING
    }

    fun remainingHealthPercentage(): Int {
        val energy = Widgets.component(WIDGETS_ROOT_ID, OpenWintertodtConstants.WIDGETS_ENERGY)

        if (energy == Component.Nil) {
            return 0
        }

        val energyText = energy.text()
        return try {
            val parsedText = energyPercentage.find(energyText)!!.groupValues[1]
            Integer.parseInt(parsedText)
        } catch (ex: Exception) {
            0
        }
    }
}