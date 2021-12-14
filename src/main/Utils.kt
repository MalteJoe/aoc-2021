import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.useLines
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

/** Read the input and print results with timings of the solutions */
@Suppress("EXPERIMENTAL_IS_NOT_ENABLED")
@OptIn(ExperimentalTime::class)
fun <I, O> advent(dir: String, parse: (Sequence<String>) -> I, part1: (I) -> O, part2: (I) -> O) {
    val (input, parseTime) = measureTimedValue { readInput(dir, parse) }
    println("Input read in $parseTime")
    val (soluti0n, durati0n) = measureTimedValue { part1(input) }
    println("Part 1: $soluti0n (took $durati0n)")
    val (solut1on, durat1on) = measureTimedValue { part2(input) }
    println("Part 2: $solut1on (took $durat1on)")
}

/** Reads lines from the given input txt file and transforms the input */
fun <T> readInput(name: String, block: (Sequence<String>) -> T) =
    Path("src/main/$name", "input").useLines(block = block)

/**
 * Create all pairwise combinations. Does not include reverse combinations. (e.g. [1,2,3] yields (1,3) but not (3,1))
 * @param withSelf include combination of element with itself (e.g. [1,2,3] yields (2,2) at some point)
 */
fun <T> List<T>.allCombinations(withSelf: Boolean = false): Sequence<Pair<T, T>> =
    sequence { forEachIndexed { i, a -> subList((if (withSelf) 0 else 1) + i, size).forEach { yield(Pair(a, it)) } } }

/** Calculate the median (rounding down) */
fun <T : Comparable<T>> Collection<T>.median(): T = sorted()[size / 2]

/** Convert a String to a List of Chars */
fun String.charList(): List<Char> = toCharArray().toList()

/** Convert a String to a List of Chars and transforms them */
inline fun <T> String.charList(crossinline mapper: (Char) -> T): List<T> = toCharArray().map(mapper)

/** Returns minimum and maximum of the Iterable in a single pass */
inline fun <T, R : Comparable<R>> Iterable<T>.minMaxOf(selector: (T) -> R): Pair<R, R> {
    return Pair(minOf(selector), maxOf(selector))
}

/** Range with may go negative steps */
fun orderIndependentRange(start: Int, end: Int) =
    if (start <= end) start..end
    else IntProgression.fromClosedRange(start, end, -1)

/** Swap a pairs first and second value */
fun <T, U> Pair<U, T>.swap(): Pair<T, U> = Pair(this.second, this.first)

/** Calculate a histogram of the characters in this CharSequence */
fun CharSequence.freqs(): Map<Char, Int> = groupingBy { it }.eachCount()

/** Calculate a histogram of the elements in this Iterable */
fun <T> Iterable<T>.freqs(): Map<T, Int> = groupingBy { it }.eachCount()

/** Converts string to md5 hash. */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
