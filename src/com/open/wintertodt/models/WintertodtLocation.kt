package com.open.wintertodt.models

import com.open.wintertodt.OpenWintertodtConstants.TILE_NE_BRAZIER
import com.open.wintertodt.OpenWintertodtConstants.TILE_NE_ROOTS
import com.open.wintertodt.OpenWintertodtConstants.TILE_NE_SAFESPOT
import com.open.wintertodt.OpenWintertodtConstants.TILE_NW_BRAZIER
import com.open.wintertodt.OpenWintertodtConstants.TILE_NW_ROOTS
import com.open.wintertodt.OpenWintertodtConstants.TILE_NW_SAFESPOT
import com.open.wintertodt.OpenWintertodtConstants.TILE_SE_BRAZIER
import com.open.wintertodt.OpenWintertodtConstants.TILE_SE_ROOTS
import com.open.wintertodt.OpenWintertodtConstants.TILE_SE_SAFESPOT
import com.open.wintertodt.OpenWintertodtConstants.TILE_SW_BRAZIER
import com.open.wintertodt.OpenWintertodtConstants.TILE_SW_ROOTS
import com.open.wintertodt.OpenWintertodtConstants.TILE_SW_SAFESPOT
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_NE_BURNER
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_NE_PYRO
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_NW_BURNER
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_NW_PYRO
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_SE_BURNER
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_SE_PYRO
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_SW_BURNER
import com.open.wintertodt.OpenWintertodtConstants.WIDGETS_SW_PYRO
import org.powbot.api.Tile

enum class WintertodtLocation(
    val rootsTile: Tile,
    val brazierTile: Tile,
    val safespotTile: Tile,
    val minBrazierX: Int,
    val maxBrazierX: Int,
    val brazierY: Int,
    val pyroComponentId: Int,
    val brazierComponentId: Int,
) {
    SOUTH_WEST(TILE_SW_ROOTS, TILE_SW_BRAZIER, TILE_SW_SAFESPOT, 1620, 1622, 3996, WIDGETS_SW_PYRO, WIDGETS_SW_BURNER),
    SOUTH_EAST(TILE_SE_ROOTS, TILE_SE_BRAZIER, TILE_SE_SAFESPOT, 1638, 1640, 3996, WIDGETS_SE_PYRO, WIDGETS_SE_BURNER),
    NORTH_EAST(TILE_NE_ROOTS, TILE_NE_BRAZIER, TILE_NE_SAFESPOT, 1638, 1640, 4018, WIDGETS_NE_PYRO, WIDGETS_NE_BURNER),
    NORTH_WEST(TILE_NW_ROOTS, TILE_NW_BRAZIER, TILE_NW_SAFESPOT, 1620, 1622, 4018, WIDGETS_NW_PYRO, WIDGETS_NW_BURNER),
}