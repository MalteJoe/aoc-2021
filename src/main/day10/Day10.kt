package day10

import util.*

/** [Syntax Scoring](https://adventofcode.com/2021/day/10) */
fun main() = advent("day10", ::mapInput, ::part1, ::part2)

typealias Input = List<String>
typealias Output = Long

fun mapInput(lines: Sequence<String>): Input = lines.toList()

fun part1(input: Input): Output = input.sumOf { processLine(it).first?.score() ?: 0 }.toLong()

fun processLine(line: String): Pair<Char?, List<Char>?> {
    val stack = mutableListOf<Char>()
    for (c in line.chars().mapToObj(::Char)) {
        when (c) {
            in "([{<" -> stack.add(c)
            else -> if (stack.isEmpty() || stack.removeLast() != c.openingBracket()) return Pair(c, null)
        }
    }
    return Pair(null, stack)
}

private fun Char.score(): Int = when (this) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    '(' -> 1
    '[' -> 2
    '{' -> 3
    '<' -> 4
    else -> error("No score for $this")
}

private fun Char.openingBracket(): Char = when (this) {
    ')' -> '('
    ']' -> '['
    '}' -> '{'
    '>' -> '<'
    else -> error("No opening Bracket for $this")
}

fun part2(input: Input): Output = input.mapNotNull { processLine(it).second }
    .map { chars -> chars.reversed().map(Char::score).fold(0L) { acc, score -> acc * 5L + score } }
    .median()
