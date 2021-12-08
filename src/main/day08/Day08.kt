package day08

import advent

/**
 * [Seven Segment Search](https://adventofcode.com/2021/day/8)
 */
fun main() = advent("day08", ::mapInput, ::part1, ::part2)

typealias Input = List<Display>
typealias Output = Int

data class Display(val patterns: List<String>, val outputValue: List<String>)

fun mapInput(lines: Sequence<String>): Input {
    val whitespace = Regex("\\W+")
    return lines.map {
        val (patternPart, outputPart) = it.split('|')
        val patterns = patternPart.trim().split(whitespace)
        val output = outputPart.trim().split(whitespace)
        Display(patterns, output)
    }.toList()
}

fun part1(input: Input): Output = input.sumOf { line -> line.outputValue.count { it.length in setOf(2, 3, 4, 7) } }

fun part2(input: Input): Output {
    TODO()
}


