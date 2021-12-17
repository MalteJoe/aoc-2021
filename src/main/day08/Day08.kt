package day08

import util.*
import kotlin.streams.*

/**
 * [Seven Segment Search](https://adventofcode.com/2021/day/8)
 */
fun main() = advent("day08", ::mapInput, ::part1, ::part2)

typealias Input = List<Display>
typealias Output = Int

data class Display(val patterns: List<String>, val outputValue: List<String>)

fun mapInput(lines: Sequence<String>): Input {
    val whitespace = Regex("\\W+")
    return lines.map {
        val (patternPart, outputPart) = it.split('|')
        val patterns = patternPart.trim().split(whitespace)
        val output = outputPart.trim().split(whitespace)
        Display(patterns, output)
    }.toList()
}

fun part1(input: Input): Output = input.sumOf { line -> line.outputValue.count { it.length in setOf(2, 3, 4, 7) } }

fun part2(input: Input): Output {
    return input.sumOf { line ->
        val charSets: List<Set<Char>> = line.patterns.map { it.charList().toSet() }
        val characterCounts = charSets.flatten().groupingBy { it }.eachCount()
        val unscramble: MutableMap<Char, Char> = mutableMapOf()

        val digitOne = line.patterns.withIndex().first { it.value.length == 2 }
        val digitFour = line.patterns.withIndex().first { it.value.length == 4 }

        val wireE = characterCounts.entries.first { it.value == 4 }.key
        unscramble[wireE] = 'e'
        val wireB = characterCounts.entries.first { it.value == 6 }.key
        unscramble[wireB] = 'b'
        val wireF = characterCounts.entries.first { it.value == 9 }.key
        unscramble[wireF] = 'f'

        val wireDAndG = characterCounts.entries.filter { it.value == 7 }
        val wireD = wireDAndG.first { digitFour.value.contains(it.key) }.key
        unscramble[wireD] = 'd'
        val wireG = wireDAndG.first { it.key != wireD }.key
        unscramble[wireG] = 'g'

        val wireAAndC = characterCounts.entries.filter { it.value == 8 }
        val wireC = wireAAndC.first { digitOne.value.contains(it.key) }.key
        unscramble[wireC] = 'c'
        val wireA = wireAAndC.first { it.key != wireC }.key
        unscramble[wireA] = 'a'


        var result = 0
        line.outputValue.forEach { scrambled ->
            result *= 10
            result += segmentDisplays.indexOf(scrambled.charList().map { unscramble[it] }.toSet())
        }
        result
    }
}

private fun String.charList(): List<Char> = chars().mapToObj(::Char).toList()

private val segmentDisplays: List<Set<Char>> = listOf(
    "abcefg", // 0
    "cf", // 1
    "acdeg", // 2
    "acdfg", // 3
    "bcdf", // 4
    "abdfg", // 5
    "abdefg", // 6
    "acf", // 7
    "abcdefg", // 8
    "abcdfg", // 9
).map(String::charList).map(List<Char>::toSet)


