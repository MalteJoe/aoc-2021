package day06

import advent

/**
 * [Lanternfish](https://adventofcode.com/2021/day/6)
 */
fun main() = advent("day06", ::mapInput, ::part1, ::part2)

typealias Input = List<Int>
typealias Output = Int

fun mapInput(lines: Sequence<String>): Input = lines.first().split(',').map(String::toInt)

fun part1(input: Input, days: Int = 80): Output {
    val state = input.toMutableList()
    repeat(days) {
        var new = 0
        state.indices.forEach { index ->
            if (--state[index] < 0) {
                state[index] = 6
                new++
            }
        }
        state += List(new) { 8 }
    }
    return state.size
}

fun part2(input: Input): Output {
    TODO()
}


