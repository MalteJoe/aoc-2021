package day08

import util.readInput
import kotlin.test.*

internal class Day08KtTest {

    private val sampleInput: String =
        """be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce"""

    @Test
    fun parseInput() {
        assertEquals(
            Display(
                listOf("be", "cfbegad", "cbdgef", "fgaecd", "cgeb", "fdcge", "agebfd", "fecdb", "fabcd", "edb"),
                listOf("fdgacbe", "cefdb", "cefbgd", "gcbe")
            ), mapInput(sampleInput.lineSequence())[0]
        )
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(26, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day08", ::mapInput)
        assertEquals(440, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(61229, part2(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day08", ::mapInput)
        assertEquals(1046281, part2(input))
    }
}
