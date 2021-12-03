package day03

import readInput

typealias Input = List<String>
typealias Output = Int

/**
 * [Binary Diagnostic](https://adventofcode.com/2021/day/3)
 */
fun main() {
    val input = readInput("day03", ::mapInput)
    println(part1(input))
    println(part2(input))
}


fun mapInput(lines: Sequence<String>): Input = lines.toList()

/**
 * Use the binary numbers in your diagnostic report to calculate the gamma rate and epsilon rate,
 * then multiply them together. **What is the power consumption of the submarine?**
 * (Be sure to represent your answer in decimal, not binary.)
 */
fun part1(input: Input): Output {
    val countOnes = Array(input.first().length) { 0 }
    for (it in input) {
        it.forEachIndexed { index, c ->
            if (c == '1') countOnes[index]++
        }
    }
    var gamma = 0
    var epsilon = 0
    for ((index, count) in countOnes.withIndex()) {
        val bit = 1 shl input.first().length - 1 - index
        if (count > input.size / 2) {
            gamma = gamma or bit
        } else {
            epsilon = epsilon or bit
        }
    }
    return gamma * epsilon
}

/**
 * TODO
 */
fun part2(input: Input): Output {
    TODO()
}


