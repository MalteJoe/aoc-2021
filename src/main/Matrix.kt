import java.util.*
import kotlin.math.abs

class Matrix<T> {
    private val values: MutableList<MutableList<T>>
    val rows: Int
    val cols: Int

    constructor(values: List<List<T>>) {
        assert(values.isNotEmpty())
        this.rows = values.size
        val firstRow = values.first()
        this.cols = firstRow.size
        values.forEach { assert(it.size == cols) }
        this.values = MutableList(values.size) { i -> values[i].toMutableList() }
    }

    constructor(rows: Int, cols: Int, init: (Coordinate) -> T) {
        assert(rows > 0)
        assert(cols > 0)
        this.rows = rows
        this.cols = cols
        this.values = MutableList(rows) { row -> MutableList(cols) { col -> init(Coordinate(row, col)) } }
    }

    fun coordinates() = sequence {
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                yield(Coordinate(row, col))
            }
        }
    }

    inline fun coordinates(crossinline predicate: (T) -> Boolean) = sequence {
        for (row in 0 until rows) {
            for (col in 0 until cols) {
                val c = Coordinate(row, col)
                if (predicate(get(c))) yield(c)
            }
        }
    }

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

    fun <R> map(mapper: (T) -> R): Matrix<R> = Matrix(values.map { it.map(mapper) })

    fun replaceAll(block: (T) -> T) = values.forEach { it.replaceAll(block) }

    fun flatten(): List<T> = values.flatten()

    fun copy(): Matrix<T> = Matrix(values)

    operator fun contains(coord: Coordinate): Boolean = coord.col in 0 until cols && coord.row in 0 until rows

    operator fun get(c: Coordinate): T = values[c.row][c.col]

    operator fun set(c: Coordinate, value: T) {
        values[c.row][c.col] = value
    }

    fun any(predicate: (T) -> Boolean) = flatten().any(predicate)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Matrix<*>

        if (values != other.values) return false

        return true
    }

    override fun hashCode(): Int {
        return values.hashCode()
    }

    override fun toString(): String {
        val padLength = flatten().map { it.toString() }.maxOf(String::length)
        val builder = StringJoiner("\n")
        values.map { it -> it.joinToString(" ", "[", "]") { it.toString().padStart(padLength) } }
            .forEach(builder::add)
        return builder.toString()
    }

    data class Coordinate(val row: Int, val col: Int)
}

operator fun Matrix<Int>.plusAssign(matrix: Matrix<Int>) {
    assert(cols == matrix.cols)
    assert(rows == matrix.rows)
    coordinates().forEach { this[it] += matrix[it] }
}

operator fun Matrix<Int>.plusAssign(toAdd: Int) = replaceAll { it + toAdd }

