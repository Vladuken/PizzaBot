package com.vladuken.pizzabot.model
interface PizzaBot {
    fun calculatePath(grid: Grid, points: List<Point>): List<Command>
}