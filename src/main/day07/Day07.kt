package day07

import advent
import median
import kotlin.math.*

/**
 * [The Treachery of Whales](https://adventofcode.com/2021/day/7)
 */
fun main() = advent("day07", ::mapInput, ::part1, ::part2)

typealias Input = List<Int>
typealias Output = Int

fun mapInput(lines: Sequence<String>): Input = lines.first().split(',').map(String::toInt)

fun part1(input: Input): Output {
    val median = input.median()
    return input.sumOf { (it - median).absoluteValue }
}

fun part2(input: Input): Output {
    TODO()
}
