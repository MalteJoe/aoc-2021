package day13

import util.readInput
import kotlin.test.*

internal class Day13Test {

    private val sampleInput: String = """6,10
          |0,14
          |9,10
          |0,3
          |10,4
          |4,11
          |6,0
          |6,12
          |4,1
          |0,13
          |10,12
          |3,4
          |3,0
          |8,4
          |1,10
          |2,14
          |8,10
          |9,0

          |fold along y=7
          |fold along x=5
""".trimMargin()


    @Test
    fun parseInput() {
        val mappedInput = mapInput(sampleInput.lineSequence())
        assertEquals(18, mappedInput.first.size)
        assertEquals(listOf(Dot(6, 10), Dot(0, 14), Dot(9, 10)), mappedInput.first.slice(0..2))
        assertEquals(listOf(Fold('y', 7), Fold('x', 5)), mappedInput.second)
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(17, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day13", ::mapInput)
        assertEquals(850, part1(input))
    }
}
