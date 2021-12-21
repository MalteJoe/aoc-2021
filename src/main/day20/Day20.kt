package day20

import util.*

/** [Trench Map](https://adventofcode.com/2021/day/20) */
fun main() = advent("day20", ::mapInput, ::part1, ::part2)

data class Input(val algorithm: String, val image: Matrix<Char>)
typealias Output = Int

typealias Pixel = Matrix.Coordinate

fun mapInput(lines: Sequence<String>): Input {
    val allLines = lines.toList()
    return Input(allLines[0], Matrix(allLines.subList(2, allLines.size).map { it.map { it } }))
}

fun part1(input: Input, steps: Int = 2): Output {
    val (image) = (0 until steps).fold(Pair(input.image, '.')) { (image, outside), step ->
        val newImage = Matrix(image.rows.size + 2, image.cols.size + 2) { (row, col) ->
            val area = Matrix(3, 3) { (r, c) ->
                val pixel = Pixel(r + row - 2, c + col - 2)
                if (pixel in image) image[pixel] else outside
            }
            input.algorithm[area.flatten().map { if (it == '#') '1' else '0' }.joinToString("").toInt(2)]
        }
        if ((step + 1).mod(10) == 0) println("After Step ${step + 1}:\n$newImage")
        Pair(newImage, if (outside == '#') input.algorithm.last() else input.algorithm[0])
    }
    return image.coordinates { it == '#' }.count()
}

fun part2(input: Input): Output = part1(input, 50)
