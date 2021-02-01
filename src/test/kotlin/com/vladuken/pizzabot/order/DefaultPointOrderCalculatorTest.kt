package com.vladuken.pizzabot.order

import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point
import org.junit.Assert.assertArrayEquals
import org.junit.Test

class DefaultPointOrderCalculatorTest {

    private val pointOrderCalculator = DefaultPointOrderCalculator()
    private val grid = Grid(5, 5)
    private val startPoint = Point(0, 0)

    @Test
    fun testEmptyList() {
        val resultList = pointOrderCalculator.calculatePointsOrder(grid, startPoint, emptyList())
        assert(resultList.isEmpty())
    }

    @Test
    fun testNotEmptyList() {
        val list = listOf(
            Point(4, 2),
            Point(1, 4),
            Point(0, 2),
        )
        val resultList = pointOrderCalculator.calculatePointsOrder(grid, startPoint, list)

        assertArrayEquals(list.toTypedArray(), resultList.toTypedArray())
    }

}