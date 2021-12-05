package day05

import allCombinations
import readInput
import kotlin.math.*
import kotlin.ranges.IntProgression.Companion.fromClosedRange
import kotlin.time.*

typealias Input = List<Line>
typealias Output = Int

/**
 * [Hydrothermal Venture](https://adventofcode.com/2021/day/5)
 */
@Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@OptIn(ExperimentalTime::class)
fun main() {
    val (input, parseTime) = measureTimedValue { readInput("day05", ::mapInput) }
    println("Input read in $parseTime")
    val (part1, part1Duration) = measureTimedValue { part1(input) }
    println("Part 1: $part1 (took $part1Duration)")
    val (part2, part2Duration) = measureTimedValue { part2(input) }
    println("Part 2: $part2 (took $part2Duration)")
}

val parser = Regex("""(\d+),(\d+) -> (\d+),(\d+)""")
fun mapInput(lines: Sequence<String>): Input = lines.map {
    val (x1, y1, x2, y2) = parser.find(it)?.destructured ?: error("Error parsing $it")
    Pair(Coordinate(x1, y1), Coordinate(x2, y2))
}.toList()

typealias Line = Pair<Coordinate, Coordinate>

data class Coordinate(val x: Int, val y: Int) {
    constructor(x: String, y: String) : this(x.toInt(), y.toInt())

    operator fun rangeTo(second: Coordinate): List<Coordinate> {
        return when (this.x) {
            second.x -> fromClosedRange(this.y, second.y, (second.y - this.y).sign).map { this.copy(y = it) }
            else -> fromClosedRange(this.x, second.x, (second.x - this.x).sign).map { this.copy(x = it) }
        }
    }

}

fun part1(input: Input): Output {
    val coordinatesWithOverlaps: MutableSet<Coordinate> = mutableSetOf()
    for ((a, b) in input.filter { (a, b) -> a.x == b.x || a.y == b.y }.allCombinations()) {
        coordinatesWithOverlaps += a.overlapsWith(b)
    }
    return coordinatesWithOverlaps.size
}

fun Line.overlapsWith(other: Line): Set<Coordinate> = (this.first..this.second).filter { it in other }.toSet()

operator fun Line.contains(other: Coordinate): Boolean = other in (this.first..this.second)

fun part2(input: Input): Output {
    TODO()
}


