package day14

import advent
import freqs
import minMaxOf

/** [Extended Polymerization](https://adventofcode.com/2021/day/14) */
fun main() = advent("day14", ::mapInput, ::part1, ::part2)

data class Input(val template: String, val insertionRules: List<Rule>)
typealias Output = Int

typealias Rule = Pair<Pair<Char, Char>, Char>

fun mapInput(lines: Sequence<String>): Input {
    val allLines = lines.toList()
    val ruleParser = Regex("""^(\p{Lu})(\p{Lu}) -> (\p{Lu})$""")
    val rules = allLines.subList(2, allLines.size).map {
        val (first, second, insert) = ruleParser.find(it)?.destructured ?: error("Error parsing $it")
        Rule(Pair(first[0], second[0]), insert[0])
    }
    return Input(allLines[0], rules)
}

fun part1(input: Input, steps: Int = 10): Output {
    var polymer = input.template
    val rules = input.insertionRules.toMap()
    repeat(steps) {
        polymer = expand(polymer, rules)
    }
    val (min, max) =  polymer.freqs().values.minMaxOf { it }
    return max - min
}

fun expand(polymer: String, rules: Map<Pair<Char, Char>, Char>): String {
    val builder = StringBuilder()
    polymer.windowed(2, partialWindows = true).forEach { pair ->
        builder.append(pair[0])
        if (pair.length != 1) {
            val insert = rules[Pair(pair[0], pair[1])]
            if (insert != null) builder.append(insert)
        }
    }
    return builder.toString()
}

fun part2(input: Input): Output = TODO()
