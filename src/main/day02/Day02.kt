package day02

import util.advent

typealias Input = List<Command>
typealias Output = Int

data class Command(val instruction: String, val units: Int)

/**
 * [Dive!](https://adventofcode.com/2021/day/2)
 */
fun main() = advent("day02", ::mapInput, ::part1, ::part2)


fun mapInput(lines: Sequence<String>): Input = lines.map { line ->
    val (instruction, units) = line.split(' ')
    Command(instruction, units.toInt())
}.toList()

/**
 * Calculate the horizontal position and depth you would have after following the planned course.
 * **What do you get if you multiply your final horizontal position by your final depth?**
 */
fun part1(input: Input): Output {
    var horizontalPos = 0
    var depth = 0
    input.forEach {
        when (it.instruction) {
            "forward" -> horizontalPos += it.units
            "down" -> depth += it.units
            "up" -> depth -= it.units
        }
    }
    return horizontalPos * depth
}

/**
 * Using this new interpretation of the commands, calculate the horizontal position and depth you would have after
 * following the planned course.
 * **What do you get if you multiply your final horizontal position by your final depth?**
 */
fun part2(input: Input): Output {
    var horizontalPos = 0
    var depth = 0
    var aim = 0
    input.forEach {
        when (it.instruction) {
            "forward" -> {
                horizontalPos += it.units
                depth += aim * it.units
            }
            "down" -> aim += it.units
            "up" -> aim -= it.units
        }
    }
    return horizontalPos * depth
}


