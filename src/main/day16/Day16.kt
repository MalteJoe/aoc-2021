package day16

import advent
import java.io.*

/** [Packet Decoder](https://adventofcode.com/2021/day/16) */
fun main() = advent("day16", ::mapInput, ::part1, ::part2)

typealias Input = String
typealias Output = Int

interface Packet {
    val version: Int
    val type: Int
    val subPackets: List<Packet>
}

data class Literal(override val version: Int, val value: Long) : Packet {
    override val type: Int get() = 4
    override val subPackets: List<Packet> get() = emptyList()
}

data class Operator(
    override val version: Int,
    override val type: Int,
    override val subPackets: List<Packet>
) : Packet

fun mapInput(lines: Sequence<String>): Input = lines.first()

fun String.hexToBinary() = map { it.digitToInt(16).toString(2).padStart(4, '0') }.joinToString("")
fun Packet.versionSum(): Int = version + subPackets.sumOf(Packet::versionSum)

fun part1(input: Input): Output = parsePacket(input.hexToBinary().reader()).versionSum()

fun parsePacket(bitstream: StringReader): Packet {
    val version = bitstream.read(3).toInt(2)
    val type = bitstream.read(3).toInt(2)
    if (type == 4) return Literal(version, parseLiteralValue(bitstream))
    val subPackets = if (bitstream.read() == '0'.code) {
        val bitLength = bitstream.read(15).toInt(2)
        parseSubpackets(bitstream, bitLength)
    } else {
        val numberOfPackets = bitstream.read(11).toInt(2)
        parseNSubpackets(bitstream, numberOfPackets)
    }
    return Operator(version, type, subPackets)
}

private fun StringReader.read(count: Int): String = String(CharArray(count).let { read(it); it })

fun parseSubpackets(bitstream: StringReader, length: Int): List<Packet> {
    val subPacketReader = StringReader(bitstream.read(length))
    val subPackets = mutableListOf<Packet>()
    while (subPacketReader.read() != -1) {
        subPacketReader.reset()
        subPackets.add(parsePacket(subPacketReader))
        subPacketReader.mark(0)
    }
    return subPackets
}

fun parseNSubpackets(bitstream: StringReader, count: Int): List<Packet> = (0 until count).map { parsePacket(bitstream) }

fun parseLiteralValue(bitstream: StringReader): Long {
    var block: String
    val builder = StringBuilder()
    do {
        block = bitstream.read(5)
        builder.append(block.substring(1))
    } while (block[0] != '0')
    return builder.toString().toLong(2)
}

fun part2(input: Input): Output = TODO()
