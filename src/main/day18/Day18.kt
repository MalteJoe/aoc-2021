package day18

import util.*
import java.io.StringReader
import kotlin.math.sign

/** [Snailfish](https://adventofcode.com/2021/day/18) */
fun main() = advent("day18", ::mapInput, ::part1, ::part2) { "${it.magnitude()} $it" }

typealias Input = List<Number>
typealias Output = Number

fun mapInput(lines: Sequence<String>): Input = lines.map(String::reader).map(::parseNumber).toList()

fun part1(input: Input): Output = input.reduce { acc, number -> (acc + number).reduced() }

fun part2(input: Input): Output = input.allCombinations(commutative = false)
    .map { (x, y) -> (x + y).reduced() }
    .maxByOrNull(Number::magnitude)!!

fun parseNumber(reader: StringReader): Number {
    assert(reader.read() == '['.code)
    val left = parseValue(reader)
    assert(reader.read() == ','.code)
    val right = parseValue(reader)
    assert(reader.read() == ']'.code)
    return Number(left, right)
}

fun parseValue(reader: StringReader): Value {
    return if (reader.peek() == '[') parseNumber(reader)
    else Regular(reader.read().toChar().digitToInt())
}

fun StringReader.peek(): Char {
    mark(0)
    return read().toChar().also { reset() }
}


interface Value {
    fun magnitude(): Int
    operator fun plus(value: Value): Value
    fun split(): Number?
}

data class Number(val left: Value, val right: Value) : Value {
    constructor(left: Int, right: Value) : this(Regular(left), right)
    constructor(left: Value, right: Int) : this(left, Regular(right))
    constructor(left: Int, right: Int) : this(Regular(left), Regular(right))

    fun reduced(): Number {
        exploded()?.let { return it.reduced() }
        split()?.let { return it.reduced() }
        return this
    }

    fun exploded(): Number? = exploded(0)?.newNumber as Number?
    override fun split() = left.split()?.let { copy(left = it) } ?: right.split()?.let { copy(right = it) }

    private fun exploded(depth: Int): Explosion? {
        if (depth == 4) {
            return Explosion(left as Regular, Regular(0), right as Regular)
        }
        if (left is Number) {
            val explosion = left.exploded(depth + 1)
            if (explosion != null) {
                val newNumber = if (explosion.right == null) copy(left = explosion.newNumber)
                else Number(explosion.newNumber, explosion.right + right)
                return explosion.copy(newNumber = newNumber, right = null)
            }
        }
        if (right is Number) {
            val explosion = right.exploded(depth + 1)
            if (explosion != null) {
                val newNumber = if (explosion.left == null) copy(right = explosion.newNumber)
                else Number(left + explosion.left, explosion.newNumber)
                return explosion.copy(newNumber = newNumber, left = null)
            }
        }
        return null
    }

    override fun plus(value: Value): Number = when (value) {
        is Regular -> copy(right = right + value)
        is Number -> Number(this, value)
        else -> error("unknown value $value")
    }

    override fun toString(): String = "[$left,$right]"

    override fun magnitude(): Int = 3 * left.magnitude() + 2 * right.magnitude()

    private data class Explosion(val left: Regular?, val newNumber: Value, val right: Regular?)
}

data class Regular(val value: Int) : Value {
    override fun magnitude() = value
    override fun plus(value: Value): Value = when (value) {
        is Regular -> Regular(this.value + value.value)
        is Number -> value.copy(left = this + value.left)
        else -> error("unknown value $value")
    }

    override fun split(): Number? = if (value >= 10) Number(value / 2, value / 2 + value.rem(2).sign) else null

    override fun toString(): String = value.toString()
}
