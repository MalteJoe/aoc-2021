package day21

import util.readInput
import kotlin.test.*

internal class Day21KtTest {

    @Test
    fun `part1 example`() {
        val input = Pair(4, 8)
        assertEquals(739785, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day21", ::mapInput)
        assertEquals(1073709, part1(input))
    }

    @Test
    @Ignore
    fun `part2 example`() {
        val input = Pair(4, 8)
        assertEquals(TODO(), part2(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day21", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
