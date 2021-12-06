package day06

import readInput
import kotlin.test.*

internal class Day06Test {

    private val sampleInput: String = "3,4,3,1,2"

    @Test
    fun `part1 example after 18 days`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(26, part1(input, days = 18))
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(5934, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day06", ::mapInput)
        assertEquals(380243, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(26984457539, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day06", ::mapInput)
        assertEquals(1708791884591, part2(input))
    }
}
