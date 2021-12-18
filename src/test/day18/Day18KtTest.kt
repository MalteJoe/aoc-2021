package day18

import util.readInput
import kotlin.test.*

internal class Day18KtTest {

    @Test
    fun parseInput() {
        val input = mapInput("[9,[8,7]]\n[[[[1,2],[3,4]],[[5,6],[7,8]]],9]".lineSequence())
        assertEquals(2, input.size)
        assertEquals(Number(9, Number(8, 7)), input[0])
        assertEquals(
            Number(
                Number(
                    Number(Number(1, 2), Number(3, 4)),
                    Number(Number(5, 6), Number(7, 8))
                ),
                9
            ),
            input[1]
        )
    }

    @Test
    fun `explode left`() {
        val input = parseNumber("[[[[[9,8],1],2],3],4]".reader())
        val expected = parseNumber("[[[[0,9],2],3],4]".reader())
        assertEquals(expected, input.exploded())
    }

    @Test
    fun `explode right`() {
        val input = parseNumber("[7,[6,[5,[4,[3,2]]]]]".reader())
        val expected = parseNumber("[7,[6,[5,[7,0]]]]".reader())
        assertEquals(expected, input.exploded())
    }

    @Test
    fun `explode middle`() {
        val input = parseNumber("[[6,[5,[4,[3,2]]]],1]".reader())
        val expected = parseNumber("[[6,[5,[7,0]]],3]".reader())
        assertEquals(expected, input.exploded())
    }

    @Test
    fun `explode middle only once`() {
        val input = parseNumber("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]".reader())
        val expected = parseNumber("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]".reader())
        assertEquals(expected, input.exploded())
    }

    @Test
    fun split() {
        assertEquals(null, Regular(5).split())
        assertEquals(Number(5, 5), Regular(10).split())
        assertEquals(Number(5, 6), Regular(11).split())
        assertEquals(Number(Number(5, 6), 12), Number(11, 12).split())
        assertEquals(Number(5, Number(6, 6)), Number(5, 12).split())
    }

    @Test
    fun `part1 small examples`() {
        var input = mapInput("[[[[4,3],4],4],[7,[[8,4],9]]]\n[1,1]".lineSequence())
        assertEquals(parseNumber("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]".reader()), input.sum())

        input = mapInput(
            """[1,1]
              |[2,2]
              |[3,3]
              |[4,4]""".trimMargin().lineSequence()
        )
        assertEquals(parseNumber("[[[[1,1],[2,2]],[3,3]],[4,4]]".reader()), input.sum())

        input = mapInput(
            """[1,1]
              |[2,2]
              |[3,3]
              |[4,4]
              |[5,5]""".trimMargin().lineSequence()
        )
        assertEquals(parseNumber("[[[[3,0],[5,3]],[4,4]],[5,5]]".reader()), input.sum())

        input = mapInput(
            """[1,1]
              |[2,2]
              |[3,3]
              |[4,4]
              |[5,5]
              |[6,6]""".trimMargin().lineSequence()
        )
        assertEquals(parseNumber("[[[[5,0],[7,4]],[5,5]],[6,6]]".reader()), input.sum())
    }

    @Test
    fun `part1 larger example`() {
        val input = mapInput(
            """[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]
              |[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]
              |[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]
              |[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]
              |[7,[5,[[3,8],[1,4]]]]
              |[[2,[2,2]],[8,[8,1]]]
              |[2,9]
              |[1,[[[9,3],9],[[9,0],[0,7]]]]
              |[[[5,[7,4]],7],1]
              |[[[[4,2],2],6],[8,7]]""".trimMargin().lineSequence()
        )
        val finalNumber = input.sum()
        assertEquals(parseNumber("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]".reader()), finalNumber)
    }

    @Test
    fun magnitude() {
        assertEquals(29, parseNumber("[9,1]".reader()).magnitude())
        assertEquals(21, parseNumber("[1,9]".reader()).magnitude())
        assertEquals(129, parseNumber("[[9,1],[1,9]]".reader()).magnitude())
        assertEquals(143, parseNumber("[[1,2],[[3,4],5]]".reader()).magnitude())
        assertEquals(1384, parseNumber("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]".reader()).magnitude())
        assertEquals(445, parseNumber("[[[[1,1],[2,2]],[3,3]],[4,4]]".reader()).magnitude())
        assertEquals(791, parseNumber("[[[[3,0],[5,3]],[4,4]],[5,5]]".reader()).magnitude())
        assertEquals(1137, parseNumber("[[[[5,0],[7,4]],[5,5]],[6,6]]".reader()).magnitude())
        assertEquals(3488, parseNumber("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]".reader()).magnitude())
    }

    private val lastAssignment =
        """[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]
          |[[[5,[2,8]],4],[5,[[9,9],0]]]
          |[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]
          |[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]
          |[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]
          |[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]
          |[[[[5,4],[7,7]],8],[[8,3],8]]
          |[[9,3],[[9,9],[6,[4,9]]]]
          |[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]
          |[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]""".trimMargin().lineSequence()

    @Test
    fun `part1 example`() {
        val finalNumber = mapInput(lastAssignment).sum()
        assertEquals(parseNumber("[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]".reader()), finalNumber)
        assertEquals(4140, finalNumber.magnitude())
    }

    @Test
    fun part1() {
        val input = readInput("day18", ::mapInput)
        assertEquals(4137, part1(input))
    }

    @Test
    fun `part2 example`() {
        val input = mapInput(lastAssignment)
        assertEquals(
            parseNumber("[[[[7,8],[6,6]],[[6,0],[7,7]]],[[[7,8],[8,8]],[[7,9],[0,6]]]]".reader()),
            findLargestSum(input)
        )

        assertEquals(3993, part2(input))
    }

    @Test
    fun part2() {
        val input = readInput("day18", ::mapInput)
        assertEquals(4573, part2(input))
    }
}
