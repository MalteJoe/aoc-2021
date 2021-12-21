package day19

import util.*
import kotlin.io.path.*
import kotlin.test.*

internal class Day19KtTest {

    private fun readTestInput() = Path("src/test/day19", "exampleInput").useLines(block = ::mapInput)

    @Test
    fun parseInput() {
        val input = readTestInput()
        assertEquals(5, input.size)
        assertEquals((0..4).toList(), input.map { it.index })
        assertEquals(listOf(25, 25, 26, 25, 26), input.map { it.beacons.size })
        assertEquals(Vector(404, -588, -901), input[0].beacons[0])
        assertEquals(Vector(459, -707, 401), input[0].beacons[24])
    }

    @Test
    fun allRotationsTest() {
        assertEquals(24, allRotations.toSet().size)

        val rotationSample = mapInput(
            """--- scanner 0 ---
              |-1,-1,1
              |-2,-2,2
              |-3,-3,3
              |-2,-3,1
              |5,6,-4
              |8,0,7""".trimMargin().lineSequence()
        )
        val rotations = allRotations.map { rot -> rotationSample[0].beacons.map { rot * it.homogenous() } }
        assertTrue(
            listOf(
                Vector(-1, -1, 1, 1),
                Vector(-2, -2, 2, 1),
                Vector(-3, -3, 3, 1),
                Vector(-2, -3, 1, 1),
                Vector(5, 6, -4, 1),
                Vector(8, 0, 7, 1),
            ) in rotations
        )
        assertTrue(
            listOf(
                Vector(1, -1, 1, 1),
                Vector(2, -2, 2, 1),
                Vector(3, -3, 3, 1),
                Vector(2, -1, 3, 1),
                Vector(-5, 4, -6, 1),
                Vector(-8, -7, 0, 1),
            ) in rotations
        )
    }

    @Test
    fun `part1 only scanner 0 and 1`() {
        val input = readTestInput()
        assertEquals(50 - 12, part1(input.subList(0, 2)).size)
    }

    @Test
    fun `part1 example`() {
        val input = readTestInput()
        assertEquals(79, part1(input).size)
    }

    @Test
    fun part1() {
        val input = readInput("day19", ::mapInput)
        assertEquals(313, part1(input).size)
    }

    @Test
    @Ignore
    fun `part2 example`() {
        val input = readTestInput()
        assertEquals(TODO(), part2(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day19", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
