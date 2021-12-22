package day20

import util.*
import kotlin.test.*

internal class Day20KtTest {

    private val sampleInput: String =
        "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##" +
                "#..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###" +
                ".######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#." +
                ".#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#....." +
                ".#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.." +
                "...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#....." +
                "..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#\n\n" +
                """
                    #..#.
                    #....
                    ##..#
                    ..#..
                    ..###""".trimIndent()


    @Test
    fun parseInput() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(512, input.algorithm.length)
        assertEquals('#', input.algorithm[34])
        assertEquals(
            Matrix(
                "#..#.".map { it },
                "#....".map { it },
                "##..#".map { it },
                "..#..".map { it },
                "..###".map { it }),
            input.image
        )
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(35, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day20", ::mapInput)
        assertEquals(4968, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(3351, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day20", ::mapInput)
        assertEquals(16793, part2(input))
    }
}
