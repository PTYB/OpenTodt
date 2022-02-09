package com.open.wintertodt.extensions

import org.powbot.api.rt4.GameObject
import org.powbot.api.rt4.Objects

fun Objects.nearestGameObject(vararg name: String): GameObject {
    return stream().name(*name).nearest().first()
}