package day11

import Matrix
import advent
import charStream
import plusAssign

/** [Dumbo Octopus](https://adventofcode.com/2021/day/11) */
fun main() = advent("day11", ::mapInput, ::part1, ::part2)

typealias Input = Matrix<Int>
typealias Output = Int


fun mapInput(lines: Sequence<String>): Input = Matrix(lines.map { it.charStream(Char::digitToInt) }.toList())

fun part1(input: Input, steps: Int = 100): Output {
    var totalFlashes = 0
    val state = input.copy()
    repeat(steps) {
        state.tick()
        totalFlashes += state.flatten().count { it == 0 }
    }
    return totalFlashes
}

fun Matrix<Int>.tick() {
    this += 1
    while (any { level -> level > 9 }) {
        for (coordinate in coordinates { it > 9 }) {
            this[coordinate] = 0
            neighbours(coordinate).filter { this[it] > 0 }.forEach { this[it] += 1 }
        }
    }
}

fun part2(input: Input): Output {
    val state = input.copy()
    var steps = 0
    while (state.flatten().count { it == 0 } < input.rows * input.cols) {
        state.tick()
        steps++
    }
    return steps
}
