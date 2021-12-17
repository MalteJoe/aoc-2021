package day12

import util.readInput
import kotlin.test.*

internal class Day12KtTest {

    private val shortExample =
        """start-A
          |start-b
          |A-c
          |A-b
          |b-d
          |A-end
          |b-end""".trimMargin()

    private val slightlyLargerExample =
        """dc-end
          |HN-start
          |start-kj
          |dc-start
          |dc-HN
          |LN-dc
          |HN-end
          |kj-sa
          |kj-HN
          |kj-dc""".trimMargin()

    private val evenLargerExample =
        """fs-end
          |he-DX
          |fs-he
          |start-DX
          |pj-DX
          |end-zg
          |zg-sl
          |zg-pj
          |pj-he
          |RW-he
          |fs-DX
          |pj-RW
          |zg-RW
          |start-pj
          |he-WI
          |zg-he
          |pj-fs
          |start-RW""".trimMargin()

    @Test
    fun `part1 short example`() {
        val input = mapInput(shortExample.lineSequence())
        assertEquals(10, part1(input))
    }

    @Test
    fun `part1 slightly larger example`() {
        val input = mapInput(slightlyLargerExample.lineSequence())
        assertEquals(19, part1(input))
    }

    @Test
    fun `part1 even larger example`() {
        val input = mapInput(evenLargerExample.lineSequence())
        assertEquals(226, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day12", ::mapInput)
        assertEquals(4707, part1(input))
    }

    @Test
    fun `part2 short example`() {
        val input = mapInput(shortExample.lineSequence())
        assertEquals(36, part2(input))
    }

    @Test
    fun `part2 slightly larger example`() {
        val input = mapInput(slightlyLargerExample.lineSequence())
        assertEquals(103, part2(input))
    }

    @Test
    fun `part2 even larger example`() {
        val input = mapInput(evenLargerExample.lineSequence())
        assertEquals(3509, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day12", ::mapInput)
        assertEquals(130493, part2(input))
    }
}
