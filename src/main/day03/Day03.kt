package day03

import advent

typealias Input = List<String>
typealias Output = Int

/**
 * [Binary Diagnostic](https://adventofcode.com/2021/day/3)
 */
fun main() = advent("day03", ::mapInput, ::part1, ::part2)


fun mapInput(lines: Sequence<String>): Input = lines.toList()

/**
 * Use the binary numbers in your diagnostic report to calculate the gamma rate and epsilon rate,
 * then multiply them together. **What is the power consumption of the submarine?**
 * (Be sure to represent your answer in decimal, not binary.)
 */
fun part1(input: Input): Output {
    var gamma = 0
    var epsilon = 0
    for ((index, count) in countOnes(input).withIndex()) {
        val bit = 1 shl input.first().length - 1 - index
        if (count > input.size / 2) {
            gamma = gamma or bit
        } else {
            epsilon = epsilon or bit
        }
    }
    return gamma * epsilon
}

private fun countOnes(input: Input): Array<Int> {
    val countOnes = Array(input.first().length) { 0 }
    for (it in input) {
        it.forEachIndexed { index, c ->
            if (c == '1') countOnes[index]++
        }
    }
    return countOnes
}

/**
 * Use the binary numbers in your diagnostic report to calculate the oxygen generator rating and CO2 scrubber rating,
 * then multiply them together. **What is the life support rating of the submarine?**
 * (Be sure to represent your answer in decimal, not binary.)
 */
fun part2(input: Input): Output {
    val oxygenGeneratorRating = searchByBitCriteria(input) { it }
    val co2ScrubberRating = searchByBitCriteria(input) { mostCommonDigit -> '0' + ('1' - mostCommonDigit) }

    return oxygenGeneratorRating * co2ScrubberRating
}

private fun searchByBitCriteria(input: Input, digitToKeep: (Char) -> Char): Int {
    var remainingNumbers = input.toMutableList()
    var bitIndex = 0
    while (remainingNumbers.size > 1) {
        val mostCommonDigit = if (countOnes(remainingNumbers)[bitIndex] * 2 >= remainingNumbers.size) '1' else '0'
        val keep = digitToKeep(mostCommonDigit)
        remainingNumbers = remainingNumbers.filter { it[bitIndex] == keep }.toMutableList()

        if (++bitIndex == input.first().length && remainingNumbers.size > 1) error("No solution")
    }
    return remainingNumbers.first().toInt(2)
}
