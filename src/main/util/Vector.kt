package util

data class Vector<T>(val x: T, val y: T) {
    inline fun <S, R> plus(vector: Vector<S>, plusOp: (T, S) -> R): Vector<R> =
        Vector(plusOp(x, vector.x), plusOp(y, vector.y))
}

operator fun Vector<Int>.plus(vector: Vector<Int>) = this.plus(vector, Int::plus)