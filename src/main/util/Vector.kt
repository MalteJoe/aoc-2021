package util

data class Vector<T>(val values: List<T>) {
    constructor(vararg values: T) : this(values.asList())

    val x get() = values[0]
    val y get() = values[1]
    val z get() = values[2]

    inline fun <S, R> apply(vector: Vector<S>, op: (T, S) -> R): Vector<R> {
        assert(vector.values.size == this.values.size)
        return Vector(values.zip(vector.values).map { op(it.first, it.second) })
    }
}

operator fun Vector<Int>.plus(vector: Vector<Int>) = apply(vector, Int::plus)
operator fun Vector<Int>.minus(vector: Vector<Int>) = apply(vector, Int::minus)
fun Vector<Int>.dotProduct(v: Vector<Int>): Int {
    assert(values.size == v.values.size)
    return values.indices.sumOf { values[it] * v.values[it] }
}