package day01

import readInput

typealias Input = List<Int>
typealias Output = Int

fun mapInput(lines: Sequence<String>): Input = lines.map(String::toInt).toList()

/**
 * **How many measurements are larger than the previous measurement?**
 */
fun part1(input: Input): Output {
    var increases = 0
    input.windowed(2).forEach { (a, b) ->
        if (b > a) {
            increases++
        }
    }
    return increases
}

/**
 * Consider sums of a three-measurement sliding window. **How many sums are larger than the previous sum?**
 */
fun part2(input: Input): Output {
    var sumOfPreviousWindow: Int? = null
    var increases = 0
    for ((a, b, c) in input.windowed(3)) {
        val sumOfWindow = a + b + c
        if (sumOfPreviousWindow != null && sumOfWindow > sumOfPreviousWindow) {
            increases++
        }
        sumOfPreviousWindow = sumOfWindow
    }
    return increases
}

/**
 * [Sonar Sweep](https://adventofcode.com/2021/day/1)
 */
fun main() {
    val input = readInput("day01", ::mapInput)
    println(part1(input))
    println(part2(input))
}


