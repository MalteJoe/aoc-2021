package day22

import util.*
import kotlin.math.*

/** [Reactor Reboot](https://adventofcode.com/2021/day/22) */
fun main() = advent("day22", ::mapInput, ::part1, ::part2)

typealias Input = List<Step>
typealias Output = Long

data class Step(val on: Boolean, val cuboid: Cuboid)
typealias Cuboid = Vector<IntRange>

fun mapInput(lines: Sequence<String>): Input {
    val parser = Regex("""(on|off) x=(-?\d+)\.\.(-?\d+),y=(-?\d+)\.\.(-?\d+),z=(-?\d+)\.\.(-?\d+)""")
    return lines.map { line ->
        val groups = parser.find(line)?.groupValues ?: error("Error parsing $line")
        val cuboid = Vector(groups.subList(2, 8).map(String::toInt).chunked(2) { (from, to) -> from..to })
        Step(groups[1] == "on", cuboid)
    }.toList()
}

fun part1(input: Input, region: Cuboid? = Cuboid(-50..50, -50..50, -50..50)): Output {
    val stepsToConsider = if (region == null) input else input.filter { region.intersect(it.cuboid) != null }
    return normalizeSteps(stepsToConsider).sumOf { it.cuboid.cubeCount * if (it.on) 1 else -1 }
}

fun part2(input: Input): Output = part1(input, region = null)

private fun normalizeSteps(input: Input): MutableList<Step> {
    val normalizedSteps = mutableListOf<Step>()
    for (step in input) {
        // Insert steps to invert the intersection volumes
        normalizedSteps += normalizedSteps.mapNotNull { previous ->
            previous.cuboid.intersect(step.cuboid)?.let { Step(!previous.on, it) }
        }
        if (step.on) normalizedSteps.add(step)
    }
    return normalizedSteps
}

internal fun Cuboid.intersect(cuboid: Cuboid): Cuboid? {
    if (!(x.overlaps(cuboid.x) && y.overlaps(cuboid.y) && z.overlaps(cuboid.z))) return null
    return Cuboid(
        max(x.first, cuboid.x.first)..min(x.last, cuboid.x.last),
        max(y.first, cuboid.y.first)..min(y.last, cuboid.y.last),
        max(z.first, cuboid.z.first)..min(z.last, cuboid.z.last)
    )
}

private val Cuboid.cubeCount: Long get() = x.size.toLong() * y.size * z.size
private fun IntRange.overlaps(other: IntRange): Boolean = first <= other.last && last >= other.first
private val IntRange.size: Int get() = last - start + 1
