import kotlin.test.*

internal class UtilsKtTest {

    @Test
    fun allCombinations() {
        assertEquals(listOf(), listOf(1).allCombinations().toList())
        assertEquals(listOf(Pair(1, 2)), listOf(1, 2).allCombinations().toList())
        assertEquals(listOf(Pair(1, 2), Pair(1, 3), Pair(2, 3)), listOf(1, 2, 3).allCombinations().toList())
        assertEquals(
            listOf(Pair(1, 2), Pair(1, 3), Pair(1, 4), Pair(2, 3), Pair(2, 4), Pair(3, 4)),
            listOf(1, 2, 3, 4).allCombinations().toList()
        )
    }
}
