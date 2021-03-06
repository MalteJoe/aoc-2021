package day10

import util.readInput
import kotlin.test.*

internal class Day10KtTest {

    private val sampleInput: String = """[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]"""

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(26397, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day10", ::mapInput)
        assertEquals(339411, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(288957, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day10", ::mapInput)
        assertEquals(2289754624, part2(input))
    }
}
