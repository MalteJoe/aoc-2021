package day01

import advent


/**
 * [Sonar Sweep](https://adventofcode.com/2021/day/1)
 */
fun main() = advent("day01", ::mapInput, ::part1, ::part2)

typealias Input = List<Int>
typealias Output = Int

fun mapInput(lines: Sequence<String>): Input = lines.map(String::toInt).toList()

/**
 * **How many measurements are larger than the previous measurement?**
 */
fun part1(input: Input): Output = input.windowed(2).count { (a, b) -> b > a }

/**
 * Consider sums of a three-measurement sliding window. **How many sums are larger than the previous sum?**
 */
fun part2(input: Input): Output =
    // two measurements overlap, so they can be omitted
    input.windowed(4).count { (a, _, _, b) -> b > a }


