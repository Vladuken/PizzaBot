package com.vladuken.pizzabot

import com.vladuken.pizzabot.model.Command
import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point

interface IPizzaBot {
    fun calculatePath(grid: Grid, points: List<Point>): List<Command>
}