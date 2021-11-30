package day01

import readInput

typealias Input = List<String>
typealias Output = Int

fun mapInput(lines: Sequence<String>): Input = lines.toList()

fun part1(input: Input): Output {
    return input.size
}

fun part2(input: Input): Output {
    TODO()
}

fun main() {
    val input = readInput("day01", ::mapInput)
    println(part1(input))
    println(part2(input))
}


