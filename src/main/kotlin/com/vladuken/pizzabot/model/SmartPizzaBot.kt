package com.vladuken.pizzabot.model

import kotlin.math.abs

class SmartPizzaBot(
    private val startPoint: Point = INITIAL_POINT
) : PizzaBot {
    companion object {
        private val INITIAL_POINT = Point(0, 0)
    }

    override fun calculatePath(grid: Grid, points: List<Point>): List<Command> {
        return calculatePath(startPoint, points)
    }

    private fun calculatePath(startPoint: Point, points: List<Point>): List<Command> {
        val resultCommandList = mutableListOf<Command>()

        var currentPoint = startPoint
        points.forEach { destinationPoint ->
            val xDiv = destinationPoint.x - currentPoint.x
            val yDiv = destinationPoint.y - currentPoint.y

            val xAxisCommands = when {
                xDiv < 0 -> Array(abs(xDiv)) { Command.W }
                xDiv > 0 -> Array(abs(xDiv)) { Command.E }
                else -> emptyArray()

            }

            resultCommandList.addAll(xAxisCommands)

            val yAxisCommands = when {
                yDiv < 0 -> Array(abs(yDiv)) { Command.S }
                yDiv > 0 -> Array(abs(yDiv)) { Command.N }
                else -> emptyArray()
            }

            resultCommandList.addAll(yAxisCommands)

            resultCommandList.add(Command.D)

            currentPoint = destinationPoint
        }


        return resultCommandList
    }

}
