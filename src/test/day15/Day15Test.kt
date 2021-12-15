package day15

import readInput
import kotlin.test.*

internal class Day15Test {

    private val sampleInput: String =
        """1163751742
          |1381373672
          |2136511328
          |3694931569
          |7463417111
          |1319128137
          |1359912421
          |3125421639
          |1293138521
          |2311944581""".trimMargin()

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(40, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day15", ::mapInput)
        assertEquals(619, part1(input))
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
        val input = readInput("day15", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
