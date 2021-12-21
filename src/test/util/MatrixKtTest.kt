package util

import kotlin.test.*

internal class MatrixKtTest {
    @Test
    fun matrixMultiplication() {
        val a = Matrix(listOf(listOf(1, 2, 3), listOf(4, 5, 6)))
        val b = Matrix(listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6)))

        val product = a * b

        assertEquals(Matrix(listOf(listOf(22, 28), listOf(49, 64))), product)
    }

    @Test
    fun matrixVectorMultiplication() {
        val a = Matrix(listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9), listOf(10, 11, 12)))
        val b = Vector(listOf(-2, 1, 0))

        val product = a * b

        assertEquals(Vector(listOf(0, -3, -6, -9)), product)
    }
}