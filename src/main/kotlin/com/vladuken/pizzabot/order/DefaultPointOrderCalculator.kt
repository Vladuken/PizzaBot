package com.vladuken.pizzabot.order

import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point

class DefaultPointOrderCalculator : IPointOrderCalculator {

    override fun calculatePointsOrder(grid: Grid, startPoint: Point, points: List<Point>): List<Point> = points

}