package com.open.wintertodt

import org.powbot.api.Area
import org.powbot.api.Tile

object OpenWintertodtConstants {

    val TILE_CLUE_CLOAK: Tile = Tile(2614, 3065)
    val TILE_CLUE_HELM: Tile = Tile(2590, 3231) // HELM Requires other items
    val TILE_CLUE_GLOVE_BOOTS: Tile = Tile(2579, 3378)
    val TILE_CLUE_TROUSERS: Tile = Tile(2819, 3125)
    val TILE_CLUE_GARB: Tile = Tile(1595, 3628)

    val AREA_INSIDE_ARENA = Area(Tile(1606, 3967), Tile(1653, 4031))

    val BANK_TODT = Tile(1640, 3944)
    val TILE_SW_ROOTS = Tile(1621, 3989)
    val TILE_NW_ROOTS = Tile(1622, 4026)
    val TILE_SE_ROOTS = Tile(1640, 3989, 0)
    val TILE_NE_ROOTS = Tile(1639, 4026)

    val TILE_SW_BRAZIER = Tile(1621, 3998, 0)
    val TILE_NW_BRAZIER = Tile(1621, 4016)
    val TILE_SE_BRAZIER = Tile(1639, 3998, 0)
    val TILE_NE_BRAZIER = Tile(1639, 4016)

    val TILE_SW_ROOTS_SURROUNDING = listOf(
        Tile(1643, 4019),
        Tile(1620, 3989),
        Tile(1621, 3989),
        Tile(1622, 3988),
        Tile(1622, 3987)
    )

    val TILE_SE_ROOTS_SURROUNDING = listOf(
        Tile(1638, 3987),
        Tile(1638, 3988),
        Tile(1639, 3989),
        Tile(1640, 3989),
        Tile(1641, 3988)
    )
    val TILE_NW_ROOTS_SURROUNDING = listOf(
        Tile(1623, 4025),
        Tile(1623, 4024),
        Tile(1621, 4023),
        Tile(1622, 4023),
        Tile(1620, 4024)
    )

    val TILE_NE_ROOTS_SURROUNDING = listOf(
        Tile(1637, 4025),
        Tile(1637, 4024),
        Tile(1638, 4023),
        Tile(1639, 4023),
        Tile(1640, 4024),
    )

    val VARPBIT_RESPAWN = 1142

    val ACTION_CHOP = "Chop"
    val ACTION_LIGHT = "Light"
    val ACTION_FEED = "Feed"
    val ACTION_FIX = "Fix"
    val ACTION_ENTER = "Enter"

    val TOOL_KNIFE: String = "Knife"
    val TOOL_TINBERBOX: String = "Tinderbox"
    val TOOL_HAMMER: String = "Hammer"

    val ITEM_BRUMA_ROOT = "Bruma root"
    val ITEM_BRUMA_KINDLING = "Bruma kindling"

    val ID_SNOWFALL = 26690

    val OBJECT_BRUMA_ROOT = "Bruma roots"
    val OBJECT_BRAZIER = "Brazier"
    val OBJECT_BURNING_BRAZIER = "Burning brazier"
    val OBJECT_DOOR = "Doors of Dinh"

    val TILE_NEAR_DOOR_OUTSIDE = Tile(1630, 3963, 0)
    val TILE_NEAR_DOOR_INSIDE = Tile(1629, 3968, 0)

    val WIDGETS_ROOT_ID = 396
    val WIDGETS_POINTS = 7
    val WIDGETS_SW_PYRO = 8
    val WIDGETS_NW_PYRO = 9
    val WIDGETS_NE_PYRO = 10
    val WIDGETS_SE_PYRO = 11
    val WIDGETS_SW_BURNER = 12
    val WIDGETS_NW_BURNER = 13
    val WIDGETS_NE_BURNER = 14
    val WIDGETS_SE_BURNER = 15
    val WIDGETS_ENERGY = 21

    val TEXTURE_BROKEN = 1397
    val TEXTURE_UNBURNED = 1398
    val TEXTURE_BURNING = 1399
    val TEXTURE_PYROMANCERDEAD = 1400

    val AREA_NEAR_DOOR = Area(Tile(1628, 3967), Tile(1632, 3970))

    const val MESSAGE_FIXED = "You fix the brazier"
    const val MESSAGE_LIT = "You light the brazier"

    const val MESSAGE_BROKEN = "The brazier is broken and shrapnel damages you."
    const val MESSAGE_OUT = "The brazier has gone out."
    const val MESSAGE_COLD = "The cold of the Wintertodt seeps into your bones."
    const val MESSAGE_NO_BRUMA_ROOTS = "You have run out of bruma roots."

    const val LEAVE_EARLY_PERCENT =4


    const val ITEM_DRAGON_AXE = "Dragon axe"
    const val ITEM_RUNE_AXE = "Rune axe"
    const val ITEM_ADAMANT_AXE = "Adamant axe"
    const val ITEM_MITHRIL_AXE = "Mithril axe"
    const val ITEM_BLACK_AXE = "Black axe"
    const val ITEM_STEEL_AXE = "Steel axe"
    const val ITEM_IRON_AXE = "Iron axe"
    const val ITEM_BRONZE_AXE = "Bronze axe"
}