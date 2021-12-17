package day09

import util.*

/**
 * [Smoke Basin](https://adventofcode.com/2021/day/9)
 */
fun main() = advent("day09", ::mapInput, ::part1, ::part2)

typealias Input = List<List<Int>>
typealias Output = Int

fun mapInput(lines: Sequence<String>): Input = lines.map { it.charList(Char::digitToInt) }.toList()

fun part1(input: Input): Output = minima2d(input).sumOf { (_, _, v) -> v + 1 }

private fun minima2d(input: Input): List<Triple<Int, Int, Int>> {
    val rowMinima = input.withIndex().map { (y, row) ->
        row.withIndex().filter { (x, v) ->
            when (x) {
                0 -> v < row[1]
                row.size - 1 -> v < row[x - 1]
                else -> row[x - 1] > v && row[x + 1] > v
            }
        }.map { (x, v) -> Triple(x, y, v) }
    }.flatten()
    return rowMinima.filter { (x, y, v) ->
        when (y) {
            0 -> v < input[1][x]
            input.size - 1 -> v < input[y - 1][x]
            else -> input[y - 1][x] > v && input[y + 1][x] > v
        }
    }
}

fun part2(input: Input): Output = minima2d(input).map { (x, y) -> basinSize(Pair(x, y), input) }
    .sorted().reversed().slice(0..2)
    .reduce { acc, i -> acc * i }

fun basinSize(start: Pair<Int, Int>, input: Input): Int {
    val basin = mutableSetOf<Pair<Int, Int>>()
    val toExpand = ArrayDeque(listOf(start))
    while (!toExpand.isEmpty()) {
        val coord = toExpand.removeFirst()
        val (x, y) = coord
        if (input[y][x] != 9 && basin.add(coord)) {
            if (x > 0) toExpand.add(coord.copy(first = x - 1))
            if (x < input[y].size - 1) toExpand.add(coord.copy(first = x + 1))
            if (y > 0) toExpand.add(coord.copy(second = y - 1))
            if (y < input.size - 1) toExpand.add(coord.copy(second = y + 1))
        }
    }
    return basin.size
}
