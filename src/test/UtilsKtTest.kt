import kotlin.test.*

internal class UtilsKtTest {

    @Test
    fun `allCombinations without self`() {
        assertEquals(listOf(), listOf(1).allCombinations(withSelf = false).toList())
        assertEquals(listOf(Pair(1, 2)), listOf(1, 2).allCombinations(withSelf = false).toList())
        assertEquals(
            listOf(Pair(1, 2), Pair(1, 3), Pair(2, 3)),
            listOf(1, 2, 3).allCombinations(withSelf = false).toList()
        )
        assertEquals(
            listOf(Pair(1, 2), Pair(1, 3), Pair(1, 4), Pair(2, 3), Pair(2, 4), Pair(3, 4)),
            listOf(1, 2, 3, 4).allCombinations(withSelf = false).toList()
        )
    }

    @Test
    fun `allCombinations with self`() {
        assertEquals(listOf(Pair(1, 1)), listOf(1).allCombinations(withSelf = true).toList())
        assertEquals(listOf(Pair(1, 1), Pair(1, 2), Pair(2, 2)), listOf(1, 2).allCombinations(withSelf = true).toList())
        assertEquals(
            listOf(Pair(1, 1), Pair(1, 2), Pair(1, 3), Pair(2, 2), Pair(2, 3), Pair(3, 3)),
            listOf(1, 2, 3).allCombinations(withSelf = true).toList()
        )
    }
}
