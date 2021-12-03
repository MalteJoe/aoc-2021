package day03

import kotlin.test.*

internal class Day03Test {

    private val sampleInput: String =
        """00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010"""


    @Test
    fun parseInput() {
        assertEquals(
            listOf(
                0b00100,
                0b11110,
                0b10110,
                0b10111,
                0b10101,
                0b01111,
                0b00111,
                0b11100,
                0b10000,
                0b11001,
                0b00010,
                0b01010
            ), mapInput(sampleInput.lineSequence())
        )
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part2(input))
    }
}
