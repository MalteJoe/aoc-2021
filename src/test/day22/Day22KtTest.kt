package day22

import util.readInput
import kotlin.io.path.*
import kotlin.test.*

internal class Day22KtTest {

    private fun readTestInput(name: String) = Path("src/test/day22", name).useLines(block = ::mapInput)

    private val sampleInput: String = """
        on x=10..12,y=10..12,z=10..12
        on x=11..13,y=11..13,z=11..13
        off x=9..11,y=9..11,z=9..11
        on x=10..10,y=10..10,z=10..10""".trimIndent()

    @Test
    fun parseInput() {
        assertEquals(
            listOf(
                Step(true, Cuboid(10..12, 10..12, 10..12)),
                Step(true, Cuboid(11..13, 11..13, 11..13)),
                Step(false, Cuboid(9..11, 9..11, 9..11)),
                Step(true, Cuboid(10..10, 10..10, 10..10)),
            ), mapInput(sampleInput.lineSequence())
        )
    }

    @Test
    fun `part1 small example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(39, part1(input))
    }

    @Test
    fun `part1 larger example`() {
        val input = readTestInput("largerExample")
        assertEquals(590784, part1(input))
    }

    @Test
    fun `part1 example from part 2`() {
        val input = readTestInput("part2Example")
        assertEquals(474140, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day22", ::mapInput)
        assertEquals(648023, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = readTestInput("part2Example")
        assertEquals(2758514936282235, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day22", ::mapInput)
        assertEquals(1285677377848549, part2(input))
    }
}
