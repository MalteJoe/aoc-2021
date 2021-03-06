package day13

import util.advent

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
        .map { foldParser.find(it)?.destructured ?: error("Error parsing $it") }
        .map { (axis, line) -> Fold(axis[0], line.toInt()) }
    return Pair(dots, folds)
}

fun part1(input: Input): Output = foldPaper(input.first.toSet(), input.second.subList(0, 1)).size

private fun foldPaper(dots: Set<Dot>, folds: List<Fold>): Set<Dot> =
    folds.fold(dots) { acc, fold -> acc.map { it.mirror(fold) }.toSet() }

fun Dot.axis(axis: Char) = if (axis == 'x') x else y
fun Dot.mirror(fold: Fold): Dot = when {
    axis(fold.axis) <= fold.line -> this
    else -> when (fold.axis) {
        'x' -> Dot(fold.line * 2 - x, y)
        else -> Dot(x, fold.line * 2 - y)
    }
}

fun part2(input: Input): Output {
    val folded = foldPaper(input.first.toSet(), input.second)
    for (y in 0..folded.maxOf { it.y }) {
        for (x in 0..folded.maxOf { it.x }) {
            print(if (Dot(x, y) in folded) '#' else ' ')
        }
        println()
    }
    return -1
}
