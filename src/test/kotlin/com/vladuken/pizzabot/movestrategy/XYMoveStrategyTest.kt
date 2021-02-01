package com.vladuken.pizzabot.movestrategy

import com.vladuken.pizzabot.model.Command
import com.vladuken.pizzabot.model.Point
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class XYMoveStrategyTest {

    private val moveStrategy: XYMoveStrategy = XYMoveStrategy()

    @Test
    fun testCalculatePathFromCurrentPointToCurrentPoint() {
        val point = Point(0, 0)
        val commands = moveStrategy.calculateMoveList(point, point)
        assertArrayEquals(emptyArray(), commands.toTypedArray())
    }

    @Test
    fun testCalculatePathFromPointsOnSameXAxis() {
        val n = 5
        val startPoint = Point(0, 0)
        val destinationPoint = Point(n, 0)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)
        assertArrayEquals(Array<Command>(n) { Command.MoveEast }, commands.toTypedArray())
    }

    @Test
    fun testCalculatePathFromPointsOnSameXAxisBackwards() {
        val n = 5
        val startPoint = Point(n, 0)
        val destinationPoint = Point(0, 0)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)
        assertArrayEquals(Array<Command>(n) { Command.MoveWest }, commands.toTypedArray())
    }


    @Test
    fun testCalculatePathFromPointsOnSameYAxis() {
        val n = 5
        val startPoint = Point(0, 0)
        val destinationPoint = Point(0, n)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)
        assertArrayEquals(Array<Command>(n) { Command.MoveNorth }, commands.toTypedArray())
    }


    @Test
    fun testCalculatePathFromPointsOnSameYAxisBackward() {
        val n = 5
        val startPoint = Point(0, n)
        val destinationPoint = Point(0, 0)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)
        assertArrayEquals(Array<Command>(n) { Command.MoveSouth }, commands.toTypedArray())
    }

    @Test
    fun testCalculatePathFromPointsOnDiagonal() {
        val startPoint = Point(0, 0)
        val destinationPoint = Point(1, 1)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)

        assertArrayEquals(
            arrayOf(Command.MoveEast, Command.MoveNorth),
            commands.toTypedArray()
        )
    }

    @Test
    fun testCalculatePathFromPointsOnDiagonalBackward() {
        val startPoint = Point(1, 1)
        val destinationPoint = Point(0, 0)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)

        assertArrayEquals(
            arrayOf(Command.MoveWest, Command.MoveSouth),
            commands.toTypedArray()
        )
    }

    @Test
    fun testCalculatePathFromPointsOnOtherDiagonal() {
        val startPoint = Point(0, 1)
        val destinationPoint = Point(1, 0)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)

        assertArrayEquals(
            arrayOf(Command.MoveEast, Command.MoveSouth),
            commands.toTypedArray()
        )
    }

    @Test
    fun testCalculatePathFromPointsOnOtherDiagonalBackward() {
        val startPoint = Point(1, 0)
        val destinationPoint = Point(0, 1)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)

        assertArrayEquals(
            arrayOf(Command.MoveWest, Command.MoveNorth),
            commands.toTypedArray()
        )
    }


    @Test
    fun testCalculatePathFromPointsOnLongDiagonal() {
        val n = 5

        val startPoint = Point(0, 0)
        val destinationPoint = Point(n, n)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)

        val xArray = Array(n) { Command.MoveEast }
        val yArray = Array(n) { Command.MoveNorth }
        val resultArray = xArray.toList() + yArray
        assertArrayEquals(
            resultArray.toTypedArray(),
            commands.toTypedArray()
        )
    }


    @Test
    fun testCalculatePathFromPointsOnLongDiagonalBackward() {
        val n = 5

        val startPoint = Point(n, n)
        val destinationPoint = Point(0, 0)
        val commands = moveStrategy.calculateMoveList(startPoint, destinationPoint)

        val xArray = Array(n) { Command.MoveWest }
        val yArray = Array(n) { Command.MoveSouth }
        val resultArray = xArray.toList() + yArray
        assertArrayEquals(
            resultArray.toTypedArray(),
            commands.toTypedArray()
        )
    }

}