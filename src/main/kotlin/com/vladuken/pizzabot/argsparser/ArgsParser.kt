package com.vladuken.pizzabot.argsparser

import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point

class ArgsParser : IArgsParser {

    override fun parse(args: Array<String>): IArgsParser.Output {
        if (args.isEmpty()) return IArgsParser.Output.EmptyError
        val grid = parseGridString(args[0]) ?: return IArgsParser.Output.GridError

        val parsedPoints = args
            .drop(1)
            .map { parsePointString(it) }
        if (parsedPoints.contains(null)) return IArgsParser.Output.PointsError

        return IArgsParser.Output.Success(grid, parsedPoints.filterNotNull())
    }


    private fun parseGridString(gridString: String): Grid? {
        return try {
            val gridItems = gridString
                .split("x")
                .map(String::toInt)

            if (gridItems.size == 2) {
                Grid(gridItems[0], gridItems[1])
            } else {
                null
            }

        } catch (e: Exception) {
            null
        }
    }

    private fun parsePointString(pointString: String): Point? {
        return try {
            val parsedNumbers = pointString
                .trim('(', ')')
                .split(",")
                .map(String::toInt)

            if (parsedNumbers.size == 2) {
                Point(parsedNumbers[0], parsedNumbers[1])
            } else {
                null
            }

        } catch (e: Exception) {
            null
        }
    }

}
