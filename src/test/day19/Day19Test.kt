package day19

import util.readInput
import kotlin.test.*

internal class Day19Test {

    private val sampleInput: String = TODO()


    @Test
    fun parseInput() {
        assertEquals(TODO(), mapInput(sampleInput.lineSequence()))
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day19", ::mapInput)
        assertEquals(TODO(), part1(input))
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
        val input = readInput("day19", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
