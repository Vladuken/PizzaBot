package com.vladuken.pizzabot.movestrategy

import com.vladuken.pizzabot.model.Command
import com.vladuken.pizzabot.model.Point

interface IMoveStrategy {
    fun calculateMoveList(startPoint: Point, endPoint: Point): List<Command>
}