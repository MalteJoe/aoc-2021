package day10

import advent

/** [Syntax Scoring](https://adventofcode.com/2021/day/10) */
fun main() = advent("day10", ::mapInput, ::part1, ::part2)

typealias Input = List<String>
typealias Output = Int

fun mapInput(lines: Sequence<String>): Input = lines.toList()

fun part1(input: Input): Output = input.sumOf { firstIllegalCharacter(it)?.score() ?: 0 }

private fun firstIllegalCharacter(line: String): Char? {
    val stack = mutableListOf<Char>()
    for (c in line.chars().mapToObj(::Char)) {
        when (c) {
            in "([{<" -> stack.add(c)
            else -> if (stack.isEmpty() || stack.removeLast() != c.openingBracket()) return c
        }
    }
    return null
}

private fun Char.score(): Int = when (this) {
    ')' -> 3
    ']' -> 57
    '}' -> 1197
    '>' -> 25137
    else -> error("No score for $this")
}

private fun Char.openingBracket(): Char = when (this) {
    ')' -> '('
    ']' -> '['
    '}' -> '{'
    '>' -> '<'
    else -> error("No opening Bracket for $this")
}

fun part2(input: Input): Output {
    TODO()
}
