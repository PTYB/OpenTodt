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

    val TILE_SE_SAFESPOT = Tile(1638, 3988, 0)
    val TILE_SW_SAFESPOT = Tile(1622, 3988, 0)
    val TILE_NW_SAFESPOT = Tile(1620, 4025, 0)
    val TILE_NE_SAFESPOT = Tile(1640, 4025, 0)

    const val VARPBIT_RESPAWN = 1142

    const val ACTION_CHOP = "Chop"
    const val ACTION_LIGHT = "Light"
    const val ACTION_FEED = "Feed"
    const val ACTION_FIX = "Fix"
    const val ACTION_ENTER = "Enter"

    const val TOOL_KNIFE: String = "Knife"
    const val TOOL_TINBERBOX: String = "Tinderbox"
    const val TOOL_HAMMER: String = "Hammer"

    const val ITEM_BRUMA_ROOT = "Bruma root"
    const val ITEM_BRUMA_KINDLING = "Bruma kindling"
    const val ITEM_CRATE = "Supply crate"

    const val ID_SNOWFALL = 26690

    const val OBJECT_BRUMA_ROOT = "Bruma roots"
    const val OBJECT_BRAZIER = "Brazier"
    const val OBJECT_BURNING_BRAZIER = "Burning brazier"
    const val OBJECT_DOOR = "Doors of Dinh"

    val ITEMS_USELESS = arrayOf("Jug","Vial")

    val TILE_NEAR_DOOR_OUTSIDE = Tile(1630, 3963, 0)
    val TILE_NEAR_DOOR_INSIDE = Tile(1629, 3968, 0)

    const val WIDGETS_ROOT_ID = 396
    const val WIDGETS_POINTS = 7
    const val WIDGETS_SW_PYRO = 8
    const val WIDGETS_NW_PYRO = 9
    const val WIDGETS_NE_PYRO = 10
    const val WIDGETS_SE_PYRO = 11
    const val WIDGETS_SW_BURNER = 12
    const val WIDGETS_NW_BURNER = 13
    const val WIDGETS_NE_BURNER = 14
    const val WIDGETS_SE_BURNER = 15
    const val WIDGETS_ENERGY = 21

    const val TEXTURE_BROKEN = 1397
    const val TEXTURE_UNBURNED = 1398
    const val TEXTURE_BURNING = 1399
    const val TEXTURE_PYROMANCERDEAD = 1400

    val AREA_NEAR_DOOR = Area(Tile(1628, 3967), Tile(1632, 3970))

    const val MESSAGE_FIXED = "You fix the brazier"
    const val MESSAGE_LIT = "You light the brazier"

    const val MESSAGE_BROKEN = "The brazier is broken and shrapnel damages you."
    const val MESSAGE_OUT = "The brazier has gone out."
    const val MESSAGE_COLD = "The cold of the Wintertodt seeps into your bones."
    const val MESSAGE_NO_BRUMA_ROOTS = "You have run out of bruma roots."
    val MESSAGES_BROKEN_EVENT = arrayOf(MESSAGE_BROKEN, MESSAGE_OUT, MESSAGE_COLD, MESSAGE_NO_BRUMA_ROOTS)

    const val ITEM_DRAGON_AXE = "Dragon axe"
    const val ITEM_RUNE_AXE = "Rune axe"
    const val ITEM_ADAMANT_AXE = "Adamant axe"
    const val ITEM_MITHRIL_AXE = "Mithril axe"
    const val ITEM_BLACK_AXE = "Black axe"
    const val ITEM_STEEL_AXE = "Steel axe"
    const val ITEM_IRON_AXE = "Iron axe"
    const val ITEM_BRONZE_AXE = "Bronze axe"

    const val ITEM_HOOD = "Pyromancer hood"
    const val ITEM_GARB = "Pyromancer garb"
    const val ITEM_ROBE = "Pyromancer robe"
    const val ITEM_BOOTS = "Pyromancer boots"
    const val ITEM_GLOVES = "Warm gloves"

    val ITEMS_PYROMANCER = arrayOf(ITEM_HOOD, ITEM_GARB, ITEM_ROBE, ITEM_BOOTS, ITEM_GLOVES)
}