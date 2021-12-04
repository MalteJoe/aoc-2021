package day04

import readInput
import kotlin.math.min
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

/**
 * [Giant Squid](https://adventofcode.com/2021/day/4)
 */
@OptIn(ExperimentalTime::class)
fun main() {
    val (input, parseTime) = measureTimedValue { readInput("day04", ::mapInput) }
    println("Input read in $parseTime")
    val (part1, part1Duration) = measureTimedValue { part1(input) }
    println("Part 1: $part1 (took $part1Duration)")
    val (part2, part2Duration) = measureTimedValue { part2(input) }
    println("Part 2: $part2 (took $part2Duration)")
}

typealias Input = BingoGame
typealias Output = Int

data class BingoGame(val drawnNumbers: List<Int>, val bingoBoards: List<BingoBoard>)

data class BingoBoard(val board: List<List<Int>>) {

    fun checkForNewRecord(drawnNumbers: List<Int>): BingoRecord? {
        val horizontal = board.map { it.toSet() }.toSet()
        val vertical = List(board.size) { i -> board.map { it[i] }.toSet() }
        val winningSets = horizontal + vertical

        var round = min(board.size, board.first().size)
        val drawnSoFar = drawnNumbers.subList(0, round).toMutableSet()
        while (!winningSets.any(drawnSoFar::containsAll) && round < drawnNumbers.size) {
            drawnSoFar.add(drawnNumbers[round++])
        }
        return if (round == drawnNumbers.size) null else BingoRecord(this, round)
    }
}

data class BingoRecord(val board: BingoBoard, val winningRound: Int)


fun mapInput(lines: Sequence<String>): Input {
    val allLines = lines.toList()
    val drawnNumbers = allLines[0].split(',').map(String::toInt)
    val boards = allLines.asIterable().drop(1).chunked(6) {
        BingoBoard(parseMatrix(it.subList(1, 6), String::toInt))
    }.toList()
    return BingoGame(drawnNumbers, boards)
}

/**
 * Parse a list of Strings each separated by whitespace into a Matrix applying the given transformation
 */
fun <T> parseMatrix(list: List<String>, transform: (String) -> T, delimiter: Regex = Regex("[^\\w]+")): List<List<T>> {
    return List(list.size) { list[it].trim().split(delimiter).map(transform = transform) }
}

fun part1(input: Input): Output {
    var best: BingoRecord? = null
    for (it in input.bingoBoards) {
        val score = it.checkForNewRecord(input.drawnNumbers.subList(0, best?.winningRound ?: input.drawnNumbers.size))
        if (score != null) best = score
    }
    if (best == null) error("No solution")
    val unmarkedNumbers = best.board.board.flatten().filter {
        !input.drawnNumbers.subList(0, best.winningRound).contains(it)
    }
    return unmarkedNumbers.sum() * input.drawnNumbers[best.winningRound - 1]
}

fun part2(input: Input): Output {
    TODO()
}


