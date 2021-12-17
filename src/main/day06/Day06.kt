package day06

import util.*

/**
 * [Lanternfish](https://adventofcode.com/2021/day/6)
 */
fun main() = advent("day06", ::mapInput, ::part1, ::part2)

typealias Input = List<Int>
typealias Output = Long

fun mapInput(lines: Sequence<String>): Input = lines.first().split(',').map(String::toInt)

fun part1(input: Input, days: Int = 80): Output {
    val frequencyMap = input.freqs()
    val frequencies = MutableList(9) { frequencyMap[it]?.toLong() ?: 0 }
    repeat(days) {
        val spawns = frequencies[0]
        for (daysLeft in 0 until 8) {
            frequencies[daysLeft] = frequencies[daysLeft + 1]
        }
        frequencies[6] += spawns
        frequencies[8] = spawns
    }
    return frequencies.sum()
}

fun part2(input: Input): Output = part1(input, days = 256)
