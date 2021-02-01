package com.vladuken.pizzabot.movestrategy

import com.vladuken.pizzabot.model.Command
import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point

interface IMoveStrategy {
    fun calculateMoveList(grid: Grid, startPoint: Point, endPoint: Point): List<Command>
}