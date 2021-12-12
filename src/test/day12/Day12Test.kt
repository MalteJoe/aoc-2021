package day12

import readInput
import kotlin.test.*

internal class Day12Test {

    private val largerExample =
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
        val input = mapInput(
            """start-A
              |start-b
              |A-c
              |A-b
              |b-d
              |A-end
              |b-end""".trimMargin().lineSequence()
        )
        assertEquals(10, part1(input))
    }

    @Test
    fun `part1 slightly larger example`() {
        val input = mapInput(
            """dc-end
              |HN-start
              |start-kj
              |dc-start
              |dc-HN
              |LN-dc
              |HN-end
              |kj-sa
              |kj-HN
              |kj-dc""".trimMargin().lineSequence()
        )
        assertEquals(19, part1(input))
    }

    @Test
    fun `part1 even larger example`() {
        val input = mapInput(
            largerExample.lineSequence()
        )
        assertEquals(226, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day12", ::mapInput)
        assertEquals(4707, part1(input))
    }

    @Test
    @Ignore
    fun `part2 example`() {
        val input = mapInput(largerExample.lineSequence())
        assertEquals(TODO(), part2(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day12", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
