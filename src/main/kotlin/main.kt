import com.vladuken.pizzabot.IPizzaBot
import com.vladuken.pizzabot.SmartPizzaBot
import com.vladuken.pizzabot.argsparser.ArgsParser
import com.vladuken.pizzabot.argsparser.IArgsParser
import com.vladuken.pizzabot.model.Command
import com.vladuken.pizzabot.movestrategy.XYMoveStrategy
import com.vladuken.pizzabot.order.DefaultPointOrderCalculator

fun main(args: Array<String>) {
    val parser: IArgsParser = ArgsParser()
    val resultMessage = when (val output = parser.parse(args)) {
        is IArgsParser.Output.Success -> {

            val bot: IPizzaBot = SmartPizzaBot(
                pointOrderCalculator = DefaultPointOrderCalculator(),
                moveStrategy = XYMoveStrategy()
            )

            val (grid, points) = output
            val commands = bot.calculatePath(grid, points)

            commands.parseToOutput()
        }

        is IArgsParser.Output.EmptyError -> "Please provide arguments"
        is IArgsParser.Output.GridError -> "Please provide correct Grid. Example: \"5x5 (3,2) (5,1)\""
        is IArgsParser.Output.PointsError -> "Please provide correct Points. Example: \"5x5 (3,2) (5,1)\""
    }

    println(resultMessage)
}

private fun List<Command>.parseToOutput(): String {
    return joinToString("") {
        when (it) {
            Command.MoveNorth -> "N"
            Command.MoveSouth -> "S"
            Command.MoveEast -> "E"
            Command.MoveWest -> "W"
            Command.DropPizza -> "D"
        }
    }
}
