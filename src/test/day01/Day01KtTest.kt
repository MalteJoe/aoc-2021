package day01

import util.readInput
import kotlin.test.*

internal class Day01KtTest {

    private val sampleInput = """199
200
208
210
200
207
240
269
260
263"""


    @Test
    fun parseInput() {
        assertEquals(listOf(
            199,
            200,
            208,
            210,
            200,
            207,
            240,
            269,
            260,
            263), mapInput(sampleInput.lineSequence()))
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(7, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day01", ::mapInput)
        assertEquals(1709, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(5, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day01", ::mapInput)
        assertEquals(1761, part2(input))
    }
}
