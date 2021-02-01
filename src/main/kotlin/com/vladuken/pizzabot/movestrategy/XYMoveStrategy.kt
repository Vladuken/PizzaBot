package com.vladuken.pizzabot.movestrategy

import com.vladuken.pizzabot.model.Command
import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point
import kotlin.math.abs

class XYMoveStrategy : IMoveStrategy {

    override fun calculateMoveList(startPoint: Point, endPoint: Point): List<Command> {
        val resultCommandList = mutableListOf<Command>()

        val xDiv = endPoint.x - startPoint.x
        val yDiv = endPoint.y - startPoint.y

        val xAxisCommands = when {
            xDiv < 0 -> Array(abs(xDiv)) { Command.MoveWest }
            xDiv > 0 -> Array(abs(xDiv)) { Command.MoveEast }
            else -> emptyArray()
        }

        resultCommandList.addAll(xAxisCommands)

        val yAxisCommands = when {
            yDiv < 0 -> Array(abs(yDiv)) { Command.MoveSouth }
            yDiv > 0 -> Array(abs(yDiv)) { Command.MoveNorth }
            else -> emptyArray()
        }

        resultCommandList.addAll(yAxisCommands)

        return resultCommandList
    }

}