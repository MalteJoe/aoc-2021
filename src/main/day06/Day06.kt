package day06

import advent

/**
 * [Lanternfish](https://adventofcode.com/2021/day/6)
 */
fun main() = advent("day06", ::mapInput, ::part1, ::part2)

typealias Input = List<Int>
typealias Output = Long

fun mapInput(lines: Sequence<String>): Input = lines.first().split(',').map(String::toInt)

fun part1(input: Input, days: Int = 80): Output {
    val frequencies = input.groupingBy { it }.eachCount()
    return frequencies.map { (restDays, count) -> count * fishCountAfterDays(restDays, days) }.sum()
}

fun part2(input: Input): Output = part1(input, days = 256)

fun fishCountAfterDays(restDays: Int, days: Int): Long {
    if (days <= 0) return 1
    if (restDays == 0) return fishCountAfterDays(6, days - 1) + fishCountAfterDays(8, days - 1)
    return fishCountAfterDays(0, days - restDays)
}
