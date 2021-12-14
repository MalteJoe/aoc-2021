package day14

import freqs
import readInput
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
        var polymer = input.template
        val rules = input.insertionRules.toMap()

        polymer = expand(polymer, rules)
        assertEquals("NCNBCHB", polymer)

        polymer = expand(polymer, rules)
        assertEquals("NBCCNBBBCBHCB", polymer)

        polymer = expand(polymer, rules)
        assertEquals("NBBBCNCCNBBNBNBBCHBHHBCHB", polymer)

        polymer = expand(polymer, rules)
        assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB", polymer)

        polymer = expand(polymer, rules)
        assertEquals(97, polymer.length)

        repeat(5) { polymer = expand(polymer, rules) }
        assertEquals(3073, polymer.length)
        assertEquals(1749, polymer.freqs()['B'])
        assertEquals(298, polymer.freqs()['C'])
        assertEquals(161, polymer.freqs()['H'])
        assertEquals(865, polymer.freqs()['N'])
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
    @Ignore
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part2(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day14", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
