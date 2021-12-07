package day07

import readInput
import kotlin.test.*

internal class Day07Test {

    private val sampleInput: String = "16,1,2,0,4,2,7,1,2,14"

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(37, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day07", ::mapInput)
        assertEquals(344605, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(168, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day07", ::mapInput)
        assertEquals(93699985, part2(input))
    }
}
