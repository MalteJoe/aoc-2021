package day02

import kotlin.test.*

internal class Day02Test {

    private val sampleInput: String = """forward 5
down 5
forward 8
up 3
down 8
forward 2"""


    @Test
    fun parseInput() {
        assertEquals(
            listOf(
                Command("forward", 5),
                Command("down", 5),
                Command("forward", 8),
                Command("up", 3),
                Command("down", 8),
                Command("forward", 2),
            ), mapInput(sampleInput.lineSequence())
        )
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(150, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part2(input))
    }
}
