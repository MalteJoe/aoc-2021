package day11

import Matrix
import readInput
import kotlin.io.path.Path
import kotlin.io.path.useLines
import kotlin.test.*

internal class Day11Test {

    private fun readTestInput(name: String) = Path("src/test/day11", name).useLines(block = ::mapInput)

    private val sampleInput: String = """5483143223
                                        |2745854711
                                        |5264556173
                                        |6141336146
                                        |6357385478
                                        |4167524645
                                        |2176841721
                                        |6882881134
                                        |4846848554
                                        |5283751526
""".trimMargin()


    @Test
    fun parseInput() {
        assertEquals(
            Matrix(10, 10) { (row, col) -> sampleInput.lines()[row][col].digitToInt() },
            mapInput(sampleInput.lineSequence())
        )
    }

    @Test
    fun `part1 example stepwise`() {
        val input = readTestInput("step0")
        input.tick()
        assertEquals(readTestInput("step1"), input)

        input.tick()
        assertEquals(readTestInput("step2"), input)

        input.tick()
        assertEquals(readTestInput("step3"), input)

        input.tick()
        assertEquals(readTestInput("step4"), input)

        input.tick()
        assertEquals(readTestInput("step5"), input)

        input.tick()
        assertEquals(readTestInput("step6"), input)

        input.tick()
        assertEquals(readTestInput("step7"), input)

        input.tick()
        assertEquals(readTestInput("step8"), input)

        input.tick()
        assertEquals(readTestInput("step9"), input)

        input.tick()
        assertEquals(readTestInput("step10"), input)
    }

    @Test
    fun `part1 example after 10 steps`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(204, part1(input, steps = 10))
        repeat(10) { input.tick() }
        assertEquals(readTestInput("step10"), input)
    }

    @Test
    fun `part1 example after 100 steps`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(1656, part1(input, steps = 100))
        repeat(100) { input.tick() }
        assertEquals(readTestInput("step100"), input)
    }

    @Test
    fun part1() {
        val input = readInput("day11", ::mapInput)
        assertEquals(1655, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(195, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day11", ::mapInput)
        assertEquals(337, part2(input))
    }
}
