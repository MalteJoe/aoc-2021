package day12

import advent
import swap

/** [Passage Pathing](https://adventofcode.com/2021/day/12) */
fun main() = advent("day12", ::mapInput, ::part1, ::part2)

typealias Input = List<Connection>
typealias Output = Int
typealias Connection = Pair<Cave, Cave>
typealias Cave = String
typealias Path = List<Cave>
typealias CaveMap = Map<Cave, List<Cave>>

fun mapInput(lines: Sequence<String>): Input {
    val parser = Regex("""^(\w+)-(\w+)$""")
    return lines.map {
        val (from, to) = parser.find(it)?.destructured ?: error("Error parsing $it")
        Pair(from, to)
    }.toList()
}

fun part1(input: Input): Output {
    val map: CaveMap = input.zip(input.map(Connection::swap))
        .flatMap { (forward, reverse) -> listOf(forward, reverse) }
        .groupBy(Connection::first, Connection::second)
    return numberOfPaths(map, listOf("start"))
}

fun numberOfPaths(map: CaveMap, explored: Path): Int {
    val currentCave = explored.last()
    if (currentCave == "end") return 1
    return map[currentCave]!!.filterNot { it.isSmall() && it in explored }.sumOf { numberOfPaths(map, explored + it) }
}

fun Cave.isSmall(): Boolean = this == lowercase()

fun part2(input: Input): Output = TODO()
