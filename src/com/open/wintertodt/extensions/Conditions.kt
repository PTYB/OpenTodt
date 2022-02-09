package com.open.wintertodt.extensions

import org.powbot.api.rt4.*
import java.util.concurrent.Callable

object Conditions {
    fun expGained(skill: Int): Callable<Boolean> {
        val startExp = Skills.experience(skill)
        return Callable<Boolean> { Skills.experience(skill) > startExp }
    }
}