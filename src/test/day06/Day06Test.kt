package day06

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
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part2(input))
    }
}
