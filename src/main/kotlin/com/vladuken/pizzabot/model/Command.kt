package com.vladuken.pizzabot.model

sealed class Command {
    object MoveNorth : Command()
    object MoveSouth : Command()
    object MoveEast : Command()
    object MoveWest : Command()
    object DropPizza : Command()
}
