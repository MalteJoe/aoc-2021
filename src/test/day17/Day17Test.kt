package day17

import readInput
import kotlin.test.*

internal class Day17Test {

    private val sampleInput: String = "target area: x=20..30, y=-10..-5"


    @Test
    fun parseInput() {
        assertEquals(Area(20..30, -10..-5), mapInput(sampleInput.lineSequence()))
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(45, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day17", ::mapInput)
        assertEquals(3160, part1(input))
    }

    @Test
    @Ignore
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part2(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day17", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
