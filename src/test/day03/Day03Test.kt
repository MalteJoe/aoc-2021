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
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(198, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part2(input))
    }
}
