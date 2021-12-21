package day15

import util.*
import java.util.*

/** [Chiton](https://adventofcode.com/2021/day/15) */
fun main() = advent("day15", ::mapInput, ::part1, ::part2)

typealias Input = Matrix<Int>
typealias Output = Int

typealias Node = Matrix.Coordinate

data class Cost(val node: Node, val cost: Int)

fun mapInput(lines: Sequence<String>): Input = Matrix(lines.map { it.map(Char::digitToInt) }.toList())

fun part1(input: Input): Output {
    val start = Node(0, 0)
    val explored = mutableSetOf<Node>()
    val toExplore = PriorityQueue<Cost>(compareBy { it.cost })
    val target = Node(input.cols.size - 1, input.rows.size - 1)
    var next = Cost(start, 0)
    while (next.node != target) {
        if (explored.add(next.node)) {
            toExplore.addAll(input.neighbours(next.node, diagonal = false)
                .filter { it !in explored }
                .map { Cost(it, next.cost + input[it]) })
        }

        next = toExplore.poll()
    }
    return next.cost
}

fun part2(input: Input): Output = part1(Input(5 * input.rows.size, 5 * input.cols.size) { (x, y) ->
    val valueInInput = input[Matrix.Coordinate(x.mod(input.rows.size), y.mod(input.cols.size))]
    (valueInInput - 1 + x.div(input.rows.size) + y.div(input.cols.size)).mod(9) + 1
})
