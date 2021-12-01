package day01

import readInput

typealias Input = List<Int>
typealias Output = Int

fun mapInput(lines: Sequence<String>): Input = lines.map(String::toInt).toList()

/**
 * **How many measurements are larger than the previous measurement?**
 */
fun part1(input: Input): Output {
    return input.windowed(2).count { (a, b) -> b > a }
}

/**
 * Consider sums of a three-measurement sliding window. **How many sums are larger than the previous sum?**
 */
fun part2(input: Input): Output {
    // two measurements overlap, so they can be omitted
    return input.windowed(4).count { (a, _, _, b) -> b > a }
}

/**
 * [Sonar Sweep](https://adventofcode.com/2021/day/1)
 */
fun main() {
    val input = readInput("day01", ::mapInput)
    println(part1(input))
    println(part2(input))
}


