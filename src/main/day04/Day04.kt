package day04

import util.advent
import kotlin.math.min

/**
 * [Giant Squid](https://adventofcode.com/2021/day/4)
 */
fun main() = advent("day04", ::mapInput, ::part1, ::part2)

typealias Input = BingoGame
typealias Output = Int

data class BingoGame(val drawnNumbers: List<Int>, val bingoBoards: List<BingoBoard>)

data class BingoBoard(val grid: List<List<Int>>) {

    private val winningSets: Set<Set<Int>> by lazy {
        val horizontal = grid.map { it.toSet() }.toSet()
        val vertical = List(grid.size) { i -> grid.map { it[i] }.toSet() }
        horizontal + vertical
    }

    fun calculateWinningRound(drawnNumbers: List<Int>): BingoRecord? {
        var round = min(grid.size, grid.first().size)
        val drawnSoFar = drawnNumbers.subList(0, round).toMutableSet()
        while (!winningSets.any(drawnSoFar::containsAll) && round < drawnNumbers.size) {
            drawnSoFar.add(drawnNumbers[round++])
        }
        return if (round == drawnNumbers.size) null else BingoRecord(this, round)
    }
}

data class BingoRecord(val board: BingoBoard, val winningRound: Int) {
    fun score(drawnNumbers: List<Int>): Int = board.grid.flatten()
        .filterNot { drawnNumbers.subList(0, winningRound).contains(it) }
        .sum() * drawnNumbers[winningRound - 1]
}

fun mapInput(lines: Sequence<String>): Input {
    val allLines = lines.toList()
    val drawnNumbers = allLines[0].split(',').map(String::toInt)
    val boards = allLines.asIterable().drop(1).chunked(6) {
        BingoBoard(parseMatrix(it.subList(1, 6), String::toInt))
    }.toList()
    return BingoGame(drawnNumbers, boards)
}

/**
 * Parse a list of Strings each separated by whitespace into a util.Matrix applying the given transformation
 */
fun <T> parseMatrix(list: List<String>, transform: (String) -> T, delimiter: Regex = Regex("[^\\w]+")): List<List<T>> {
    return List(list.size) { list[it].trim().split(delimiter).map(transform = transform) }
}

fun part1(input: Input): Output {
    var best: BingoRecord? = null
    for (it in input.bingoBoards) {
        val drawnUpToCurrentBest = input.drawnNumbers.subList(0, best?.winningRound ?: input.drawnNumbers.size)
        best = it.calculateWinningRound(drawnUpToCurrentBest) ?: best
    }
    return best?.score(input.drawnNumbers) ?: error("No solution")
}

fun part2(input: Input): Output {
    return (input.bingoBoards.mapNotNull { it.calculateWinningRound(input.drawnNumbers) }
        .maxByOrNull { it.winningRound } ?: error("No solution"))
        .score(input.drawnNumbers)
}
