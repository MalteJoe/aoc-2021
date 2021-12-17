package day05

import kotlin.test.*

internal class Day05KtTest {

    private val sampleInput: String = """0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2"""

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(5, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(12, part2(input))
    }

    @Test
    fun `Coordinate rangeTo`() {
        val diagonal = Coordinate(9, 7)..Coordinate(7, 9)
        assertEquals(listOf(Coordinate(9, 7), Coordinate(8, 8), Coordinate(7, 9)), diagonal.toList())
    }
}
