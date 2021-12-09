package day09

import readInput
import kotlin.test.*

internal class Day09Test {

    private val sampleInput: String = """2199943210
3987894921
9856789892
8767896789
9899965678"""

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(15, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day09", ::mapInput)
        assertEquals(494, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(1134, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day09", ::mapInput)
        assertEquals(1048128, part2(input))
    }
}
