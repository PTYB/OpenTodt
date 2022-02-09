package com.open.wintertodt.helpers

import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_POINTS
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_ROOT_ID
import org.powbot.api.rt4.Widgets

object PointHelper {

    fun getPointsContributed(): Int {
        val points = Widgets.component(WIDGETS_ROOT_ID, WIDGETS_POINTS)

        if (points.text().isEmpty()) {
            return 0
        }
        val splitText = points.text().split("<br>")
        if (splitText.size < 2) {
            return 0
        }
        return splitText[1].toInt()
    }
}