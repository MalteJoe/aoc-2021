package day16

import util.readInput
import kotlin.test.*

internal class Day16Test {

    @Test
    fun `parse literal`() {
        val input = "D2FE28".hexToBinary().reader()

        val packet = parsePacket(input)

        assertEquals(6, packet.version)
        assertEquals(4, packet.type)
        assertEquals(0, packet.subPackets.size)
        assertTrue(packet is Literal)
        assertEquals(2021, packet.value)
    }

    @Test
    fun `parse operator by bits`() {
        val input = "38006F45291200".hexToBinary().reader()

        val packet = parsePacket(input)

        assertEquals(1, packet.version)
        assertEquals(6, packet.type)
        assertEquals(2, packet.subPackets.size)
        assertTrue(packet is Operator)
    }

    @Test
    fun `parse operator by packet count`() {
        val input = "EE00D40C823060".hexToBinary().reader()

        val packet = parsePacket(input)

        assertEquals(7, packet.version)
        assertEquals(3, packet.type)
        assertEquals(3, packet.subPackets.size)
        assertTrue(packet is Operator)
    }

    @Test
    fun `part1 examples`() {
        assertEquals(16, part1("8A004A801A8002F478"))
        assertEquals(12, part1("620080001611562C8802118E34"))
        assertEquals(23, part1("C0015000016115A2E0802F182340"))
        assertEquals(31, part1("A0016C880162017C3686B18A3D4780"))
    }

    @Test
    fun part1() {
        val input = readInput("day16", ::mapInput)
        assertEquals(969, part1(input))
    }

    @Test
    fun `part2 examples`() {
        assertEquals(3, part2("C200B40A82"))
        assertEquals(54, part2("04005AC33890"))
        assertEquals(7, part2("880086C3E88112"))
        assertEquals(9, part2("CE00C43D881120"))
        assertEquals(1, part2("D8005AC2A8F0"))
        assertEquals(0, part2("F600BC2D8F"))
        assertEquals(0, part2("9C005AC2F8F0"))
        assertEquals(1, part2("9C0141080250320F1802104A08"))
    }

    @Test
    fun part2() {
        val input = readInput("day16", ::mapInput)
        assertEquals(124921618408, part2(input))
    }
}
