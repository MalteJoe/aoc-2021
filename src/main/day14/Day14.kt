package day14

import util.*

/** [Extended Polymerization](https://adventofcode.com/2021/day/14) */
fun main() = advent("day14", ::mapInput, ::part1, ::part2)

data class Input(val template: String, val insertionRules: List<Rule>)
typealias Output = Long

typealias Element = Char
typealias Rule = Pair<ElementPair, Element>
typealias ElementPair = Pair<Element, Element>
typealias Histogram<T> = Map<T, Long>

fun mapInput(lines: Sequence<String>): Input {
    val allLines = lines.toList()
    val ruleParser = Regex("""^(\p{Lu})(\p{Lu}) -> (\p{Lu})$""")
    val rules = allLines.subList(2, allLines.size).map {
        val (first, second, insert) = ruleParser.find(it)?.destructured ?: error("Error parsing $it")
        Rule(ElementPair(first[0], second[0]), insert[0])
    }
    return Input(allLines[0], rules)
}

fun part1(input: Input, steps: Int = 10): Output {
    val rules = input.insertionRules.toMap()
    var pairsFrequencies = input.template.pairwiseChars().histogram()

    repeat(steps) { pairsFrequencies = expand(pairsFrequencies, rules) }

    val (min, max) = elementHistogram(pairsFrequencies, input.template.last()).values.minMaxOf { it }
    return max - min
}

fun String.pairwiseChars() = windowed(2).map { Pair(it[0], it[1]) }
fun <T> Iterable<T>.histogram() = freqs().mapValues { it.value.toLong() }

fun expand(pairsFrequencies: Histogram<ElementPair>, rules: Map<ElementPair, Element>): Histogram<ElementPair> {
    val newFrequencies = pairsFrequencies.toMutableMap()
    pairsFrequencies.keys.forEach {
        val insert = rules[it]
        if (insert != null) {
            val occurs = pairsFrequencies[it]!!
            newFrequencies.merge(it, occurs, Long::minus)
            newFrequencies.merge(ElementPair(it.first, insert), occurs, Long::plus)
            newFrequencies.merge(ElementPair(insert, it.second), occurs, Long::plus)
        }
    }
    return newFrequencies
}

fun elementHistogram(pairsFrequencies: Histogram<ElementPair>, lastElement: Element): Histogram<Element> {
    val characterFrequencies = pairsFrequencies.map { Pair(it.key.first, it.value) }.groupingBy { it.first }
        .fold(0L) { accumulator, element -> accumulator + element.second }.toMutableMap()
    characterFrequencies.merge(lastElement, 1L, Long::plus)
    return characterFrequencies
}

fun part2(input: Input, steps: Int = 40): Output = part1(input, steps)
