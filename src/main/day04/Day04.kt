package day04

import readInput

typealias Input = BingoGame
typealias Output = Int

data class BingoGame(val drawnNumbers: List<Int>, val bingoBoards: List<BingoBoard>)
data class BingoBoard(val board: List<List<Int>>)

/**
 * [Giant Squid](https://adventofcode.com/2021/day/4)
 */
fun main() {
    val input = readInput("day04", ::mapInput)
    println(part1(input))
    println(part2(input))
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
 * Parse a list of Strings each separated by whitespace into a Matrix applying the given transformation
 */
fun <T> parseMatrix(list: List<String>, transform: (String) -> T, delimiter: Regex = Regex("[^\\w]+")): List<List<T>> {
    return List(list.size) { list[it].trim().split(delimiter).map(transform = transform) }
}

fun part1(input: Input): Output {
    TODO()
}

fun part2(input: Input): Output {
    TODO()
}


