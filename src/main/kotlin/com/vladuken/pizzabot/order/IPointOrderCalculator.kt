package com.vladuken.pizzabot.order

import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point

interface IPointOrderCalculator {

    fun calculatePointsOrder(grid: Grid, startPoint: Point, points: List<Point>): List<Point>

}