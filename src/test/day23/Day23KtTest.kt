package day23

import util.readInput
import kotlin.test.*

internal class Day23KtTest {

    private val sampleInput: String = """
        #############
        #...........#
        ###B#C#B#D###
          #A#D#C#A#
          #########""".trimIndent()

    @Test
    fun mapState() {
        assertEquals(
            State("...........", mapOf('A' to "BA", 'B' to "CD", 'C' to "BC", 'D' to "DA")),
            mapInput(sampleInput.lineSequence()).toState()
        )
    }

    @Test
    fun `example step by step`() {
        // Starting configuration
        var state = mapInput(sampleInput.lineSequence()).toState()
        // One Bronze amphipod moves into the hallway, taking 4 steps and using 40 energy
        var possibleMoves = possibleMoves(state)
        var expected = State("...B.......", mapOf('A' to "BA", 'B' to "CD", 'C' to ".C", 'D' to "DA"))
        assertTrue(Pair(expected, 40) in possibleMoves)
        state = expected

        // The only Copper amphipod not in its side room moves there, taking 4 steps and using 400 energy
        possibleMoves = possibleMoves(state)
        expected = State("...B.C.....", mapOf('A' to "BA", 'B' to ".D", 'C' to ".C", 'D' to "DA"))
        assertTrue(Pair(expected, 200) in possibleMoves)
        state = expected

        possibleMoves = possibleMoves(state)
        expected = State("...B.......", mapOf('A' to "BA", 'B' to ".D", 'C' to "CC", 'D' to "DA"))
        assertTrue(Pair(expected, 200) in possibleMoves)
        state = expected

        // A Desert amphipod moves out of the way, taking 3 steps and using 3000 energy, ...
        possibleMoves = possibleMoves(state)
        expected = State("...B.D.....", mapOf('A' to "BA", 'B' to "..", 'C' to "CC", 'D' to "DA"))
        assertTrue(Pair(expected, 3000) in possibleMoves)
        state = expected

        // ... and then the Bronze amphipod takes its place, taking 3 steps and using 30 energy
        possibleMoves = possibleMoves(state)
        expected = State(".....D.....", mapOf('A' to "BA", 'B' to ".B", 'C' to "CC", 'D' to "DA"))
        assertTrue(Pair(expected, 30) in possibleMoves)
        state = expected

        // The leftmost Bronze amphipod moves to its room using 40 energy:
        possibleMoves = possibleMoves(state)
        expected = State("...B.D.....", mapOf('A' to ".A", 'B' to ".B", 'C' to "CC", 'D' to "DA"))
        assertTrue(Pair(expected, 20) in possibleMoves)
        state = expected

        possibleMoves = possibleMoves(state)
        expected = State(".....D.....", mapOf('A' to ".A", 'B' to "BB", 'C' to "CC", 'D' to "DA"))
        assertTrue(Pair(expected, 20) in possibleMoves)
        state = expected

        // Both amphipods in the rightmost room move into the hallway, using 2003 energy in total:
        possibleMoves = possibleMoves(state)
        expected = State(".....D.D...", mapOf('A' to ".A", 'B' to "BB", 'C' to "CC", 'D' to ".A"))
        assertTrue(Pair(expected, 2000) in possibleMoves)
        state = expected

        possibleMoves = possibleMoves(state)
        expected = State(".....D.D.A.", mapOf('A' to ".A", 'B' to "BB", 'C' to "CC", 'D' to ".."))
        assertTrue(Pair(expected, 3) in possibleMoves)
        state = expected

        // Both Desert amphipods move into the rightmost room using 7000 energy:
        possibleMoves = possibleMoves(state)
        expected = State(".....D...A.", mapOf('A' to ".A", 'B' to "BB", 'C' to "CC", 'D' to ".D"))
        assertTrue(Pair(expected, 3000) in possibleMoves)
        state = expected

        possibleMoves = possibleMoves(state)
        expected = State(".........A.", mapOf('A' to ".A", 'B' to "BB", 'C' to "CC", 'D' to "DD"))
        assertTrue(Pair(expected, 4000) in possibleMoves)
        state = expected

        // Finally, the last Amber amphipod moves into its room, using 8 energy
        possibleMoves = possibleMoves(state)
        expected = State("...........", mapOf('A' to "AA", 'B' to "BB", 'C' to "CC", 'D' to "DD"))
        assertTrue(Pair(expected, 8) in possibleMoves)
    }

    @Test
    fun `part1 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(12521, part1(input))
    }

    @Test
    fun part1() {
        val input = readInput("day23", ::mapInput)
        assertEquals(19046, part1(input))
    }

    @Test
    @Ignore
    fun `part2 example`() {
        val input = mapInput(sampleInput.lineSequence())
        assertEquals(TODO(), part2(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day23", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
