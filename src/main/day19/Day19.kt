package day19

import util.*
import kotlin.math.absoluteValue

/** [Beacon Scanner](https://adventofcode.com/2021/day/19) */
fun main() = advent("day19", ::mapInput, ::part1, ::part2)

typealias Input = List<Scanner>
typealias Output = Int

data class Scanner(val index: Int, val beacons: List<Vector<Int>>)
typealias Transformation = Matrix<Int>

fun mapInput(lines: Sequence<String>): Input = lines.chunked(String::isEmpty).map { scannerData ->
    val index = Regex("\\d+").find(scannerData[0])?.value?.toInt() ?: error("Invalid Scanner number ${scannerData[0]}")
    val beacons = scannerData.subList(1, scannerData.size).map {
        Vector(it.split(',').map(String::toInt))
    }
    Scanner(index, beacons)
}.toList()

fun part1(input: Input): Output {
    // Set of beacons in scanner 0's coordinate system
    return uniqueBeacons(arrangeScanners(input)).size
}

internal fun uniqueBeacons(arrangement: Map<Scanner, Transformation>) =
    arrangement.flatMap { (scanner, transformation) -> scanner.beacons.map { transformation * it.homogenous() } }
        .toSet()

internal fun arrangeScanners(input: Input): Map<Scanner, Transformation> {
    val transformations = mutableMapOf(Pair(input[0], unitMatrix(4)))
    val remainingScanners = input.subList(1, input.size).toMutableSet()

    while (remainingScanners.isNotEmpty()) {
        val (matchingScanner, transformation) = remainingScanners.asSequence().flatMap { scanner ->
            transformations.mapNotNull { (knownScanner, transformationToKnown) ->
                val inverse = findInverseTransformation(knownScanner, scanner)
                if (inverse != null) transformationToKnown * inverse else null
            }.map { Pair(scanner, it) }
        }.first()
        remainingScanners.remove(matchingScanner)
        transformations[matchingScanner] = transformation
    }
    return transformations
}

fun findInverseTransformation(known: Scanner, unknown: Scanner): Transformation? {
    val knownBeacons = known.beacons.map(Vector<Int>::homogenous)
    val unknownBeacons = unknown.beacons.map(Vector<Int>::homogenous)
    allRotations.forEach { rot ->
        knownBeacons.forEach { match ->
            unknownBeacons.forEach { beacon ->
                val offset = match - rot * beacon
                // transformation to map the unknown beacons to the known ones
                val inverseTransform = rot.withTranslation(offset)
                if (unknownBeacons.map { inverseTransform * it }.intersect(knownBeacons.toSet()).size >= 12) {
                    return inverseTransform
                }
            }
        }
    }
    return null
}

fun Vector<Int>.homogenous(): Vector<Int> = Vector(x, y, z, 1)

private fun Matrix<Int>.withTranslation(translation: Vector<Int>): Matrix<Int> = copy().also {
    it[Matrix.Coordinate(0, 3)] = translation.x
    it[Matrix.Coordinate(1, 3)] = translation.y
    it[Matrix.Coordinate(2, 3)] = translation.z
}

fun unitMatrix(dim: Int): Matrix<Int> = Matrix(dim, dim) { (col, row) -> if (col == row) 1 else 0 }

fun sin(piQuarters: Int): Int = when (piQuarters.mod(4)) {
    0 -> 0
    1 -> 1
    2 -> 0
    3 -> -1
    else -> error("How did we get here?")
}

fun cos(piQuarters: Int): Int = sin(piQuarters + 1)


val ROTATE_X: List<Matrix<Int>> = List(4) { i ->
    Matrix(
        listOf(1, 0, 0, 0),
        listOf(0, cos(i), -sin(i), 0),
        listOf(0, sin(i), cos(i), 0),
        listOf(0, 0, 0, 1)
    )
}

val ROTATE_Y: List<Matrix<Int>> = List(4) { i ->
    Matrix(
        listOf(cos(i), 0, sin(i), 0),
        listOf(0, 1, 0, 0),
        listOf(-sin(i), 0, cos(i), 0),
        listOf(0, 0, 0, 1)
    )
}

val ROTATE_Z: List<Matrix<Int>> = List(4) { i ->
    Matrix(
        listOf(cos(i), -sin(i), 0, 0),
        listOf(sin(i), cos(i), 0, 0),
        listOf(0, 0, 1, 0),
        listOf(0, 0, 0, 1)
    )
}

val allRotations by lazy {
    sequence {
        // Assuming Z is "up" and X is "front"
        // look neutral
        (0..3).forEach { this.yield(ROTATE_X[it]) }
        // look "up"
        (0..3).forEach { this.yield(ROTATE_Y[3] * ROTATE_Z[it]) }
        // look "down"
        (0..3).forEach { this.yield(ROTATE_Y[1] * ROTATE_Z[it]) }
        // turn around Z
        var currentRot = ROTATE_Z[1]
        (0..3).forEach { this.yield(currentRot * ROTATE_Y[it]) }
        currentRot *= ROTATE_Z[1]
        (0..3).forEach { this.yield(currentRot * ROTATE_X[it]) }
        currentRot *= ROTATE_Z[1]
        (0..3).forEach { this.yield(currentRot * ROTATE_Y[it]) }
    }.toList()
}

fun part2(input: Input): Output = arrangeScanners(input).values
    .map { it * Vector(0, 0, 0, 1) }
    .allCombinations()
    .maxOf { (it.first - it.second).manhattanLength() }

private fun Vector<Int>.manhattanLength(): Int = values.slice(0..2).map(Int::absoluteValue).sum()

inline fun <T> Sequence<T>.chunked(crossinline delimiter: (T) -> Boolean): Sequence<List<T>> {
    val underlyingSequence = this
    return sequence {
        val buffer = mutableListOf<T>()
        for (current in underlyingSequence) {
            if (delimiter(current)) {
                yield(buffer.toList())
                buffer.clear()
            } else {
                buffer.add(current)
            }
        }
        if (buffer.isNotEmpty()) yield(buffer)
    }
}
