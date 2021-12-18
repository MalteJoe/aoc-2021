package day18

import util.readInput
import kotlin.test.*

internal class Day18KtTest {

    private fun Number(left: Int, right: Int) = Number(Regular(left), Regular(right))
    private fun Number(left: Number, right: Int) = Number(left, Regular(right))
    private fun Number(left: Int, right: Number) = Number(Regular(left), right)

    @Test
    fun parseInput() {
        val input = mapInput("[9,[8,7]]\n[[[[1,2],[3,4]],[[5,6],[7,8]]],9]".lineSequence())
        assertEquals(2, input.size)
        assertEquals(Number(9, Number(8, 7)), input[0])
        assertEquals(
            Number(
                Number(
                    Number(Number(1, 2), Number(3, 4)),
                    Number(Number(5, 6), Number(7, 8))
                ), 9
            ), input[1]
        )
    }

    @Test
    @Ignore
    fun part1() {
        val input = readInput("day18", ::mapInput)
        assertEquals(TODO(), part1(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day18", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
