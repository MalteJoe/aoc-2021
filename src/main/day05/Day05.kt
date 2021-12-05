package day05

import advent
import allCombinations
import kotlin.math.*

/**
 * [Hydrothermal Venture](https://adventofcode.com/2021/day/5)
 */
fun main() = advent("day05", ::mapInput, ::part1, ::part2)

typealias Input = List<Line>
typealias Output = Int

val parser = Regex("""(\d+),(\d+) -> (\d+),(\d+)""")
fun mapInput(lines: Sequence<String>): Input = lines.map {
    val (x1, y1, x2, y2) = parser.find(it)?.destructured ?: error("Error parsing $it")
    Line(Coordinate(x1, y1), Coordinate(x2, y2))
}.toList()

data class Line(val start: Coordinate, val end: Coordinate) {
    private val coordinates: Set<Coordinate> by lazy { (start..end).toSet() }
    fun overlapsWith(other: Line): Set<Coordinate> = this.coordinates.intersect(other.coordinates)
}

data class Coordinate(val x: Int, val y: Int) {
    constructor(x: String, y: String) : this(x.toInt(), y.toInt())

    operator fun rangeTo(second: Coordinate): List<Coordinate> {
        return when {
            this.x == second.x -> orderIndependentRange(this.y, second.y).map { this.copy(y = it) }
            this.y == second.y -> orderIndependentRange(this.x, second.x).map { this.copy(x = it) }
            else -> orderIndependentRange(this.x, second.x)
                .zip(orderIndependentRange(this.y, second.y))
                .map { (x, y) -> Coordinate(x, y) }
        }
    }

    private fun orderIndependentRange(start: Int, end: Int) =
        IntProgression.fromClosedRange(start, end, (end - start).sign)

}

fun part1(input: Input): Output = findOverlaps(input.filter { (a, b) -> a.x == b.x || a.y == b.y }).size

fun part2(input: Input): Output = findOverlaps(input).size

fun findOverlaps(input: Input): Set<Coordinate> {
    val coordinatesWithOverlaps: MutableSet<Coordinate> = mutableSetOf()
    for ((a, b) in input.allCombinations()) {
        coordinatesWithOverlaps += a.overlapsWith(b)
    }
    return coordinatesWithOverlaps
}
