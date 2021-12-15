package day15

import Matrix
import advent
import charList
import java.util.*
import kotlin.collections.LinkedHashMap

/** [Chiton](https://adventofcode.com/2021/day/15) */
fun main() = advent("day15", ::mapInput, ::part1, ::part2)

typealias Input = Matrix<Int>
typealias Output = Int

data class Dijkstra<T>(val cost: Int, val previous: T?)
typealias Node = Matrix.Coordinate

fun mapInput(lines: Sequence<String>): Input = Matrix(lines.map { it.charList(Char::digitToInt) }.toList())

fun part1(input: Input): Output {
    val minimalRisks = LinkedHashMap<Node, Dijkstra<Node>>().apply { put(Node(0, 0), Dijkstra(0, null)) }
    val toExplore = PriorityQueue<Pair<Node, Dijkstra<Node>>>(compareBy { it.second.cost })
    var currentCost = 0
    val target = Node(input.cols - 1, input.rows - 1)
    var expand = minimalRisks.keys.last()
    while (expand != target) {
        toExplore.addAll(input.neighbours(expand, diagonal = false)
            .filter { it !in minimalRisks }
            .map { Pair(it, Dijkstra(currentCost + input[it], expand)) })

        val next = toExplore.poll()
        minimalRisks[next.first] = next.second
        toExplore.removeIf { it.first == next.first }
        currentCost = next.second.cost
        expand = minimalRisks.keys.last()
    }
    return minimalRisks.values.last().cost
}

fun part2(input: Input): Output = part1(Input(5 * input.rows, 5 * input.cols) { (x, y) ->
    (input[Matrix.Coordinate(x.mod(input.rows), y.mod(input.cols))] - 1
            + x.div(input.rows) + y.div(input.cols)).mod(9) + 1
})
