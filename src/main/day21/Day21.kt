package day21

import util.advent
import kotlin.math.min

/** [Dirac Dice](https://adventofcode.com/2021/day/21) */
fun main() = advent("day21", ::mapInput, ::part1, ::part2)

typealias Input = Pair<Int, Int>
typealias Output = Int

class DeterministicDie {
    private var _rolls = 0
    val rolls get() = _rolls

    fun roll(): Int = (_rolls++).mod(100) + 1
}

fun mapInput(lines: Sequence<String>): Input {
    val (p1, p2) = lines.map { it.substringAfterLast(' ').toInt() }.toList()
    return Pair(p1, p2)
}

fun part1(input: Input): Output {
    val die = DeterministicDie()
    var p1score = 0
    var p2score = 0
    var (p1field, p2field) = input
    while (p1score < 1000 && p2score < 1000) {
        p1field = (p1field - 1 + (0 until 3).sumOf { die.roll() }).mod(10) + 1
        p1score += p1field
        if (p1score < 1000) {
            p2field = (p2field - 1 + (0 until 3).sumOf { die.roll() }).mod(10) + 1
            p2score += p2field
        }
    }
    return die.rolls * min(p1score, p2score)
}

fun part2(input: Input): Output = TODO()
