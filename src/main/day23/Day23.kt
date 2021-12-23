package day23

import util.advent
import java.math.BigInteger.TEN
import java.util.*
import kotlin.math.*

/** [Amphipod](https://adventofcode.com/2021/day/23) */
fun main() = advent("day23", ::mapInput, ::part1, ::part2)

typealias Input = List<String>
typealias Output = Int

data class State(val hallway: String, val rooms: Map<Char, String>) {
    val isTargetState: Boolean get() = rooms.all { (room, pos) -> pos.all { it == room } }
}

fun mapInput(lines: Sequence<String>): Input = lines.toList()

fun part1(input: Input): Output = dijkstra(input.toState())

fun part2(input: Input): Output =
    dijkstra((input.toMutableList().also { it.addAll(3, listOf("  #D#C#B#A#", "  #D#B#A#C#")) }).toState())

private fun dijkstra(start: State): Int {
    val explored = mutableSetOf<State>()
    val toExplore = PriorityQueue<Pair<State, Int>>(compareBy { it.second })
    var next = Pair(start, 0)
    while (!next.first.isTargetState) {
        if (explored.add(next.first)) {
            toExplore += possibleMoves(next.first).map { it.copy(second = it.second + next.second) }
        }

        next = toExplore.poll()
    }
    return next.second
}

internal fun List<String>.toState(): State {
    return State(get(1).substring(1 until 12), "ABCD".associateWith { room ->
        val col = room.hallwayIndex + 1
        (2..size - 2).map { get(it)[col] }.joinToString("")
    })
}

internal fun possibleMoves(state: State): List<Pair<State, Int>> {
    val newStates = mutableListOf<Pair<State, Int>>()
    // moves from hallway
    state.hallway.mapIndexedNotNullTo(newStates) { index, c ->
        if (!c.isAmphipod) return@mapIndexedNotNullTo null
        // Once an amphipod stops moving in the hallway, it will stay in that spot until it can move into a room.
        // Amphipods will never move from the hallway into a room unless that room is their destination room and
        // that room contains no amphipods which do not also have that room as their own destination.
        val hallwayIndex = c.hallwayIndex
        val hallwayToDoor =
            state.hallway.substring(if (index < hallwayIndex) index + 1..hallwayIndex else hallwayIndex until index)
        if (hallwayToDoor.any(Char::isAmphipod) || state.rooms[c]!!.any { it.isAmphipod && it != c }) {
            return@mapIndexedNotNullTo null
        }
        val rooms = state.rooms.toMutableMap()
        val newPos = rooms[c]!!.lastIndexOf('.')
        rooms[c] = rooms[c]!!.withReplacement(newPos, c)
        val cost = (newPos + 1 + hallwayToDoor.length) * c.movementCost
        return@mapIndexedNotNullTo Pair(State(state.hallway.withReplacement(index, '.'), rooms), cost)
    }

    // moves from rooms
    state.rooms.flatMapTo(newStates) { (room, roomPositions) ->
        // perfect position
        if (roomPositions.all { it == room || it == '.' }) return@flatMapTo emptyList()
        // only the topmost amphipod can move
        val oldPos = roomPositions.indexOfFirst(Char::isAmphipod)
        val newRooms = state.rooms.toMutableMap()
        newRooms[room] = roomPositions.withReplacement(oldPos, '.')
        // Amphipods will never stop on the space immediately outside any room.
        listOf(0, 1, 3, 5, 7, 9, 10).filterNot {
            state.hallway.substring(
                min(it, room.hallwayIndex),
                max(it + 1, room.hallwayIndex)
            ).any(Char::isAmphipod)
        }.map { targetPos ->
            val cost = (oldPos + 1 + abs(targetPos - room.hallwayIndex)) * roomPositions[oldPos].movementCost
            Pair(State(state.hallway.withReplacement(targetPos, roomPositions[oldPos]), newRooms), cost)
        }
    }

    return newStates
}

private fun String.withReplacement(index: Int, value: Char) = toCharArray().also { it[index] = value }.concatToString()

private val Char.isAmphipod: Boolean get() = this in "ABCD"
private val Char.hallwayIndex: Int get() = (this - 'A') * 2 + 2
private val Char.movementCost: Int get() = TEN.pow(this - 'A').toInt()
