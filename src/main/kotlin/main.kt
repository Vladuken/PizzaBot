import kotlin.math.abs

fun main(args: Array<String>) {
    val bot = SmartPizzaBot()
    val grid = Grid(5, 5)

    val resultPoints = listOf<Point>(
        Point(0, 0),
        Point(1, 3),
        Point(4, 4),
        Point(4, 2),
        Point(4, 2),
        Point(0, 1),
        Point(3, 2),
        Point(2, 3),
        Point(4, 1),
    )

    val simplePoints = listOf<Point>(
        Point(1, 3),
        Point(4, 4),
    )

    val commands = bot.calculatePath(grid, simplePoints)

    println(commands.joinToString("") { it::class.java.simpleName })
}

interface PizzaBot {
    fun calculatePath(grid: Grid, points: List<Point>): List<Command>
}

class SmartPizzaBot(
    private val startPoint: Point = INITIAL_POINT
) : PizzaBot {
    companion object {
        private val INITIAL_POINT = Point(0, 0)
    }

    override fun calculatePath(grid: Grid, points: List<Point>): List<Command> {
        return calculatePath(startPoint, points)
    }

    private fun calculatePath(startPoint: Point, points: List<Point>): List<Command> {
        val resultCommandList = mutableListOf<Command>()

        var currentPoint = startPoint
        points.forEach { destinationPoint ->
            val xDiv = destinationPoint.x - currentPoint.x
            val yDiv = destinationPoint.y - currentPoint.y

            val xAxisCommands = when {
                xDiv < 0 -> Array(abs(xDiv)) { Command.W }
                xDiv > 0 -> Array(abs(xDiv)) { Command.E }
                else -> emptyArray()

            }

            resultCommandList.addAll(xAxisCommands)

            val yAxisCommands = when {
                yDiv < 0 -> Array(abs(yDiv)) { Command.S }
                yDiv > 0 -> Array(abs(yDiv)) { Command.N }
                else -> emptyArray()
            }

            resultCommandList.addAll(yAxisCommands)

            resultCommandList.add(Command.D)

            currentPoint = destinationPoint
        }


        return resultCommandList
    }

}

data class Grid(
    val width: Int,
    val height: Int
)

data class Point(
    val x: Int,
    val y: Int
)

sealed class Command {

    object N : Command()

    object S : Command()

    object E : Command()

    object W : Command()

    object D : Command()

}


