package day21

import util.*
import java.lang.Long.max
import kotlin.math.min

/** [Dirac Dice](https://adventofcode.com/2021/day/21) */
fun main() = advent("day21", ::mapInput, ::part1, ::part2)

typealias Input = Pair<Int, Int>
typealias Output = Long

data class Player(val field: Int, val score: Int)

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
    return (die.rolls * min(p1score, p2score)).toLong()
}

fun part2(input: Input): Output {
    val turnFreqs = (1..3).flatMap { d1 -> (1..3).flatMap { d2 -> (1..3).map { d3 -> d1 + d2 + d3 } } }.freqs()
    val (p1Wins, p2Wins) = countWinnerUniverses(Player(input.first, 0), Player(input.second, 0), turnFreqs)
    return max(p1Wins, p2Wins)
}

fun countWinnerUniverses(atTurn: Player, notAtTurn: Player, turnFreqs: Map<Int, Int>): Pair<Long, Long> =
    turnFreqs.entries.fold(0L to 0L) { wins, (moves, freq) ->
        val newField = (atTurn.field - 1 + moves).mod(10) + 1
        val newScore = atTurn.score + newField
        wins + if (newScore >= 21) freq.toLong() to 0 else
            countWinnerUniverses(notAtTurn, Player(newField, newScore), turnFreqs).swapped() * freq
    }

private operator fun Pair<Long, Long>.plus(pair: Pair<Long, Long>) = first + pair.first to second + pair.second

private operator fun Pair<Long, Long>.times(factor: Int) = first * factor to second * factor
