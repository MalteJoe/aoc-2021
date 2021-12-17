package day17

import advent

/** [Trick Shot](https://adventofcode.com/2021/day/17) */
fun main() = advent("day17", ::mapInput, ::part1, ::part2)

typealias Input = Area
typealias Output = Int

data class Area(val xRange: IntRange, val yRange: IntRange)

fun mapInput(lines: Sequence<String>): Input {
    val parser = Regex("""x=(-?\d+)\.\.(-?\d+), y=(-?\d+)\.\.(-?\d+)$""")
    val groups = parser.find(lines.first())?.groupValues?.let { it.subList(1, it.size) }?.map(String::toInt)
        ?: error("Error parsing line")
    return Area(groups[0]..groups[1], groups[2]..groups[3])
}

fun part1(input: Input): Output = TODO()

fun part2(input: Input): Output = TODO()
