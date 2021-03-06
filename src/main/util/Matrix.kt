package util

import java.util.*
import kotlin.math.abs

/** Fixed size but variable content Matrix */
class Matrix<T> {
    private val _values: MutableList<MutableList<T>>
    val values: List<List<T>> get() = _values
    val rows: Int
    val cols: Int

    constructor(vararg values: List<T>) : this(values.toList())

    constructor(values: List<List<T>>) {
        assert(values.isNotEmpty())
        rows = values.size
        cols = values.first().size
        assert(values.all { it.size == cols })
        this._values = MutableList(values.size) { i -> values[i].toMutableList() }
    }

    constructor(rows: Int, cols: Int, init: (Coordinate) -> T) {
        assert(rows > 0)
        this.rows = rows
        assert(cols > 0)
        this.cols = cols
        this._values = MutableList(rows) { row -> MutableList(cols) { col -> init(Coordinate(row, col)) } }
    }

    fun coordinates() = sequence {
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                yield(Coordinate(row, col))
            }
        }
    }

    inline fun coordinates(crossinline predicate: (T) -> Boolean) = coordinates().filter { predicate(get(it)) }

    fun neighbours(c: Coordinate, diagonal: Boolean = true) = sequence {
        for (dRow in -1..1) {
            for (dCol in -1..1) {
                if ((dRow != 0 || dCol != 0) && (diagonal || abs(dRow) != abs(dCol))) {
                    val coord = Coordinate(c.row + dRow, c.col + dCol)
                    if (coord in this@Matrix) yield(coord)
                }
            }
        }
    }

    fun <R> map(mapper: (T) -> R): Matrix<R> = Matrix(_values.map { it.map(mapper) })

    fun replaceAll(block: (T) -> T) = _values.forEach { it.replaceAll(block) }

    fun flatten(): List<T> = _values.flatten()

    fun copy(): Matrix<T> = Matrix(_values)

    fun transposed(): Matrix<T> = Matrix(values[0].indices.map { i -> values.map { it[i] } })

    operator fun contains(coord: Coordinate): Boolean = coord.col in 0 until cols && coord.row in 0 until rows

    operator fun get(c: Coordinate): T = _values[c.row][c.col]

    operator fun set(c: Coordinate, value: T) {
        _values[c.row][c.col] = value
    }

    fun any(predicate: (T) -> Boolean) = flatten().any(predicate)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix<*>

        if (_values != other._values) return false

        return true
    }

    override fun hashCode(): Int {
        return _values.hashCode()
    }

    override fun toString(): String {
        val padLength = flatten().map { it.toString() }.maxOf(String::length)
        val builder = StringJoiner("\n")
        _values.map { it -> it.joinToString(" ", "[", "]") { it.toString().padStart(padLength) } }.forEach(builder::add)
        return builder.toString()
    }

    data class Coordinate(val row: Int, val col: Int)
}

// Matrix<Int> calculations

operator fun Matrix<Int>.plusAssign(matrix: Matrix<Int>) {
    assert(cols == matrix.cols)
    assert(rows == matrix.rows)
    coordinates().forEach { this[it] += matrix[it] }
}

operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> {
    assert(cols == other.rows)
    val otherT = other.transposed()
    return Matrix(rows, other.cols) { (row, col) ->
        values[row].zip(otherT.values[col]).sumOf { it.first * it.second }
    }
}

operator fun Matrix<Int>.times(vector: Vector<Int>): Vector<Int> {
    assert(cols == vector.values.size)
    return Vector(values.map { vector.dotProduct(Vector(it)) })
}

operator fun Matrix<Int>.plusAssign(toAdd: Int) = replaceAll { it + toAdd }

