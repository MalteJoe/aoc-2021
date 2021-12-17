package day17

import advent
import java.lang.Math.max

/** [Trick Shot](https://adventofcode.com/2021/day/17) */
fun main() = advent("day17", ::mapInput, ::part1, ::part2)

typealias Input = Area
typealias Output = Int

data class Area(val xRange: IntRange, val yRange: IntRange) {
    operator fun contains(vector: Vector): Boolean = vector.x in xRange && vector.y in yRange
}

data class Vector(val x: Int, val y: Int) {
    fun hitsTarget(area: Area): Boolean {
        var xPos = x
        var yPos = y
        var xVelocity = x
        var yVelocity = y
        while (xPos <= area.xRange.last && yPos >= area.yRange.first) {
            if (Vector(xPos, yPos) in area) return true
            xVelocity = (xVelocity-1).coerceAtLeast(0)
            yVelocity -= 1
            xPos += xVelocity
            yPos += yVelocity
        }
        return false
    }

}

fun mapInput(lines: Sequence<String>): Input {
    val parser = Regex("""x=(-?\d+)\.\.(-?\d+), y=(-?\d+)\.\.(-?\d+)$""")
    val groups = parser.find(lines.first())?.groupValues?.let { it.subList(1, it.size) }?.map(String::toInt)
        ?: error("Error parsing line")
    return Area(groups[0]..groups[1], groups[2]..groups[3])
}

fun part1(input: Input): Output {
    // x coordinates are irrelevant for part 1.
    // We always want to shoot up, at some point yVelocity becomes 0 and visits the same y values on it's
    // way back down. So the "best" shot is reaching the target within 1 step after having same y as the origin.
    // Max Y is calculated by the triangular number formula
    return input.yRange.first * (input.yRange.first + 1) / 2
}

fun part2(input: Input): Output {
    // drag reduces xVelocity by 1 in each step, so there is a minimum to reach the target area
    val minXVelocity = triangularNumbers().first { (_, sum) -> sum in input.xRange }.first
    val maxXVelocity = input.xRange.last
    val minYVelocity = input.yRange.first
    val maxYVelocity = -minYVelocity
    var distinctValues = 0
    for (initialX in minXVelocity..maxXVelocity) {
        for (initialY in minYVelocity..maxYVelocity) {
            if (Vector(initialX, initialY).hitsTarget(input)) {
                distinctValues++
            }
        }
    }

    return distinctValues
}

fun triangularNumbers() = sequence {
    var i = 1
    var sum = 1
    do {
        yield(Pair(i, sum))
        sum += ++i
    } while (true)
}
