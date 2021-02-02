package com.vladuken.pizzabot.argsparser

import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point

interface IArgsParser {

    fun parse(args: Array<String>): Output

    sealed class Output {
        data class Success(val grid: Grid, val points: List<Point>) : Output()
        object EmptyError : Output()
        object GridError : Output()
        object PointsError : Output()
        object PointsOutOfBoundsError : Output()
    }

}