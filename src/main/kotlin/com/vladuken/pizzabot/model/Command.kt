package com.vladuken.pizzabot.model
sealed class Command {

    object N : Command()

    object S : Command()

    object E : Command()

    object W : Command()

    object D : Command()

}
