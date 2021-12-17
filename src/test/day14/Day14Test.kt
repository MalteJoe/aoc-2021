package day14

import util.*
import kotlin.test.*

internal class Day14Test {

    private val sampleInput: String = """NNCB

CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C"""


    @Test
    fun parseInput() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals("NNCB", input.template)
        assertEquals(16, input.insertionRules.size)
        assertEquals(
            listOf(Rule(Pair('C', 'H'), 'B'), Rule(Pair('H', 'H'), 'N'), Rule(Pair('C', 'B'), 'H')),
            input.insertionRules.slice(0..2)
        )
    }

    @Test
    fun `expand example`() {
        val input = mapInput(sampleInput.lineSequence())
        var polymerPairs = input.template.pairwiseChars().histogram()
        val rules = input.insertionRules.toMap()
        val lastElement = input.template.last()
        val stringHistogram = { s: String -> s.freqs().mapValues { it.value.toLong() } }

        polymerPairs = expand(polymerPairs, rules)
        assertEquals(stringHistogram("NCNBCHB"), elementHistogram(polymerPairs, lastElement))

        polymerPairs = expand(polymerPairs, rules)
        assertEquals(
            stringHistogram("NBCCNBBBCBHCB"), elementHistogram(polymerPairs, lastElement)
        )

        polymerPairs = expand(polymerPairs, rules)
        assertEquals(
            stringHistogram("NBBBCNCCNBBNBNBBCHBHHBCHB"), elementHistogram(polymerPairs, lastElement)
        )

        polymerPairs = expand(polymerPairs, rules)
        assertEquals(
            stringHistogram("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB"),
            elementHistogram(polymerPairs, lastElement)
        )

        polymerPairs = expand(polymerPairs, rules)
        assertEquals(97, polymerPairs.values.sumOf { it } + 1)

        repeat(5) { polymerPairs = expand(polymerPairs, rules) }
        assertEquals(3073, polymerPairs.values.sumOf { it } + 1)
        val elementHistogram = elementHistogram(polymerPairs, lastElement)
        assertEquals(1749, elementHistogram['B'])
        assertEquals(298, elementHistogram['C'])
        assertEquals(161, elementHistogram['H'])
        assertEquals(865, elementHistogram['N'])
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(1588, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day14", ::mapInput)
        assertEquals(4244, part1(input))
    }

    @Test
    fun `part2 after 10 steps`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(1588, part2(input, steps = 10))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(2188189693529, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day14", ::mapInput)
        assertEquals(4807056953866, part2(input))
    }
}
