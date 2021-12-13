package day13

import advent

/** [Transparent Origami](https://adventofcode.com/2021/day/13) */
fun main() = advent("day13", ::mapInput, ::part1, ::part2)

typealias Input = Pair<List<Dot>, List<Fold>>
typealias Output = Int

data class Dot(val x: Int, val y: Int)
data class Fold(val axis: Char, val line: Int)

fun mapInput(lines: Sequence<String>): Input {
    val allLines = lines.toList()
    val splitAt = allLines.indexOf("")
    val dots = allLines.subList(0, splitAt)
        .map { it.split(',').map(String::toInt).let { (x, y) -> Dot(x, y) } }
    val foldParser = Regex("""([xy])=(\d+)$""")
    val folds = allLines.subList(splitAt + 1, allLines.size)
        .mapNotNull { foldParser.find(it)?.destructured }
        .map { (axis, line) -> Fold(axis[0], line.toInt()) }
    return Pair(dots, folds)
}

fun part1(input: Input): Output = TODO()

fun part2(input: Input): Output = TODO()
