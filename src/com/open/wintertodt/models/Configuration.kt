package com.open.wintertodt.models

class Configuration(
    val foodName: String,
    val foodCount: Int,
    val minFoodCount: Int,
    val startingLocation: WintertodtLocation,
    val logsOnly: Boolean,
    val idleAtFiveHundred: Boolean,
    val snowfallSafespot: Boolean,
    val openCrates: Boolean,
    val upgradeGear: Boolean
) {
    val healthRestorePercent = 65

}