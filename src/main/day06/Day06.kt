package day06

import day04.mapInput
import readInput
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

typealias Input = Any // TODO
typealias Output = Int

/**
 * [TODO](https://adventofcode.com/2021/day/6)
 */
@Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@OptIn(ExperimentalTime::class)
fun main() {
    val (input, parseTime) = measureTimedValue { readInput("day06", ::mapInput) }
    println("Input read in $parseTime")
    val (part1, part1Duration) = measureTimedValue { part1(input) }
    println("Part 1: $part1 (took $part1Duration)")
    val (part2, part2Duration) = measureTimedValue { part2(input) }
    println("Part 2: $part2 (took $part2Duration)")
}


fun mapInput(lines: Sequence<String>): Input = TODO()

/**
 * TODO
 */
fun part1(input: Input): Output {
    TODO()
}

/**
 * TODO
 */
fun part2(input: Input): Output {
    TODO()
}


