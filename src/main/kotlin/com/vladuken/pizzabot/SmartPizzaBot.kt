package com.vladuken.pizzabot

import com.vladuken.pizzabot.model.Command
import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point
import com.vladuken.pizzabot.movestrategy.IMoveStrategy
import com.vladuken.pizzabot.order.IPointOrderCalculator

class SmartPizzaBot(
    private val initialPoint: Point = INITIAL_POINT,
    private val pointOrderCalculator: IPointOrderCalculator,
    private val moveStrategy: IMoveStrategy
) : IPizzaBot {

    companion object {
        private val INITIAL_POINT = Point(0, 0)
    }

    override fun calculatePath(grid: Grid, points: List<Point>): List<Command> {
        val resultCommandList = mutableListOf<Command>()

        val orderedPoints = pointOrderCalculator.calculatePointsOrder(grid, initialPoint, points)

        var currentPoint = initialPoint
        orderedPoints.forEach { destinationPoint ->
            val moveList = moveStrategy.calculateMoveList(grid, currentPoint, destinationPoint)

            resultCommandList.addAll(moveList)
            resultCommandList.add(Command.DropPizza)

            currentPoint = destinationPoint
        }

        return resultCommandList
    }

}
