package day12

import util.*

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

fun part1(input: Input): Output = numberOfPaths(createCaveMap(input), listOf("start")) { path, cave ->
    cave.isLarge() || cave !in path
}

fun createCaveMap(input: Input) = (input + (input.map(Connection::swap))).groupBy(Connection::first, Connection::second)

fun numberOfPaths(map: CaveMap, explored: Path, canVisit: (Path, Cave) -> Boolean): Int {
    val currentCave = explored.last()
    if (currentCave == "end") return 1
    return map[currentCave]!!.filter { canVisit(explored, it) }.sumOf { numberOfPaths(map, explored + it, canVisit) }
}

fun Cave.isLarge(): Boolean = this[0].isUpperCase()

fun part2(input: Input): Output = numberOfPaths(createCaveMap(input), listOf("start")) { path, cave ->
    cave.isLarge() || cave !in path || cave != "start" && !path.filterNot { it.isLarge() }.freqs().values.contains(2)
}
