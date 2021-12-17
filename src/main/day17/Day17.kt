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

fun part1(input: Input): Output {
    // x coordinates are irrelevant for part 1.
    // We always want to shoot up, at some point yVelocity becomes 0 and visits the sam y values on it's
    // way back down. So the "best" shot is reaching the target within 1 step after having same y as the origin.
    // Max Y is calculated by the triangular number formula
    return input.yRange.first * (input.yRange.first + 1) / 2
}

fun part2(input: Input): Output = TODO()
