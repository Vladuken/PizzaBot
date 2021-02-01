package com.vladuken.pizzabot

import com.vladuken.pizzabot.model.Command.*
import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point
import com.vladuken.pizzabot.movestrategy.IMoveStrategy
import com.vladuken.pizzabot.movestrategy.XYMoveStrategy
import com.vladuken.pizzabot.order.DefaultPointOrderCalculator
import com.vladuken.pizzabot.order.IPointOrderCalculator
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class SmartPizzaBotTest {

    private val moveStrategy: IMoveStrategy = XYMoveStrategy()
    private val pointCalculator: IPointOrderCalculator = DefaultPointOrderCalculator()

    private val bot: IPizzaBot = SmartPizzaBot(
        moveStrategy = moveStrategy,
        pointOrderCalculator = pointCalculator
    )

    private val grid = Grid(5, 5)


    @Test
    fun deliverNothing() {
        val points = emptyList<Point>()
        val commands = bot.calculatePath(grid, points)

        assertArrayEquals(
            points.toTypedArray(),
            commands.toTypedArray()
        )
    }

    @Test
    fun deliverOnePizzaWithoutMovement() {
        val points = listOf(Point(0, 0))
        val commands = bot.calculatePath(grid, points)

        assertArrayEquals(
            arrayOf(DropPizza),
            commands.toTypedArray()
        )
    }

    @Test
    fun deliverMultiplePizzasWithoutMovement() {
        val points = listOf(Point(0, 0), Point(0, 0), Point(0, 0))
        val commands = bot.calculatePath(grid, points)

        assertArrayEquals(
            arrayOf(DropPizza, DropPizza, DropPizza),
            commands.toTypedArray()
        )
    }


    @Test
    fun deliverOnePizzaWithMovement() {
        val points = listOf(Point(3, 2))
        val commands = bot.calculatePath(grid, points)

        assertArrayEquals(
            arrayOf(MoveEast, MoveEast, MoveEast, MoveNorth, MoveNorth, DropPizza),
            commands.toTypedArray()
        )
    }


    @Test
    fun deliverTwoPizzaWithMovement() {
        val points = listOf(Point(3, 2), Point(1, 1))
        val commands = bot.calculatePath(grid, points)

        assertArrayEquals(
            arrayOf(
                MoveEast,
                MoveEast,
                MoveEast,
                MoveNorth,
                MoveNorth,
                DropPizza,
                MoveWest,
                MoveWest,
                MoveSouth,
                DropPizza
            ),
            commands.toTypedArray()
        )
    }

}