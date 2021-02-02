package com.vladuken.pizzabot.argsparser

import com.vladuken.pizzabot.model.Grid
import com.vladuken.pizzabot.model.Point
import org.junit.Test
import kotlin.test.assertEquals

class ArgsParserTest {

    private val parser: IArgsParser = ArgsParser()

    @Test
    fun testNoArgs() {
        val output = parser.parse(emptyArray())
        assertEquals(output, IArgsParser.Output.EmptyError)
    }

    @Test
    fun testIncorrectGrid() {
        val argsArray = arrayOf("5x", "(2,2)", "(3,3)")
        val output = parser.parse(argsArray)
        assertEquals(output, IArgsParser.Output.GridError)
    }

    @Test
    fun testIncorrectGridNumber() {
        val argsArray = arrayOf("5.5x5", "(2,2)", "(3,3)")
        val output = parser.parse(argsArray)
        assertEquals(output, IArgsParser.Output.GridError)
    }

    @Test
    fun testIncorrectNoGrid() {
        val argsArray = arrayOf("(2,2)", "3,3")
        val output = parser.parse(argsArray)
        assertEquals(output, IArgsParser.Output.GridError)
    }


    @Test
    fun testCorrectGridNoPoints() {
        val argsArray = arrayOf("5x5")
        val output = parser.parse(argsArray)
        assertEquals(output, IArgsParser.Output.Success(Grid(5, 5), emptyList()))
    }

    @Test
    fun testCorrectGridOneIncorrectPoint() {
        val argsArray = arrayOf("5x5", "3.3")
        val output = parser.parse(argsArray)
        assertEquals(output, IArgsParser.Output.PointsError)
    }

    @Test
    fun testCorrectGridOneIncorrectPointOnlyX() {
        val argsArray = arrayOf("5x5", "(2,)")
        val output = parser.parse(argsArray)
        assertEquals(output, IArgsParser.Output.PointsError)
    }

    @Test
    fun testCorrectGridOneIncorrectPointOnlyY() {
        val argsArray = arrayOf("5x5", "(,3)")
        val output = parser.parse(argsArray)
        assertEquals(output, IArgsParser.Output.PointsError)
    }

    @Test
    fun testCorrectGridCorrectPoints() {
        val argsArray = arrayOf("5x5", "(3,3)", "(5,6)")
        val output = parser.parse(argsArray)
        assertEquals(output, IArgsParser.Output.Success(Grid(5, 5), listOf(Point(3, 3), Point(5, 6))))
    }

}