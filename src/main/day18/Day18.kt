package day18

import util.advent
import java.io.StringReader

/** [Snailfish](https://adventofcode.com/2021/day/18) */
fun main() = advent("day18", ::mapInput, ::part1, ::part2)

typealias Input = List<Number>
typealias Output = Int

interface Value
data class Number(val left: Value, val right: Value) : Value
data class Regular(val value: Int) : Value

fun mapInput(lines: Sequence<String>): Input = lines.map(String::reader).map(::parseNumber).toList()

fun part1(input: Input): Output = TODO()

fun part2(input: Input): Output = TODO()

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
