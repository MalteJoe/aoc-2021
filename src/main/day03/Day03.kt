package day03

import readInput

typealias Input = List<Int>
typealias Output = Int

/**
 * [Binary Diagnostic](https://adventofcode.com/2021/day/3)
 */
fun main() {
    val input = readInput("day03", ::mapInput)
    println(part1(input))
    println(part2(input))
}


fun mapInput(lines: Sequence<String>): Input = lines.map { it.toInt(2) }.toList()

/**
 * Use the binary numbers in your diagnostic report to calculate the gamma rate and epsilon rate,
 * then multiply them together. **What is the power consumption of the submarine?**
 * (Be sure to represent your answer in decimal, not binary.)
 */
fun part1(input: Input): Output {
    TODO()
}

/**
 * TODO
 */
fun part2(input: Input): Output {
    TODO()
}


