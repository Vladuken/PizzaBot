import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point
import com.vladuken.pizzabot.model.SmartPizzaBot

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