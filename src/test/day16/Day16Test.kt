package day16

import readInput
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
        assertEquals(TODO(), part1(input))
    }

    @Test
    @Ignore
    fun `part2 example`() {
        //val input = mapInput(sampleInput.lineSequence())
        //assertEquals(TODO(), part2(input))
    }

    @Test
    @Ignore
    fun part2() {
        val input = readInput("day16", ::mapInput)
        assertEquals(TODO(), part2(input))
    }
}
