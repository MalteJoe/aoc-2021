import java.math.*
import java.security.*
import kotlin.io.path.*

/**
 * Reads lines from the given input txt file and transforms the input
 */
fun <T> readInput(name: String, block: (Sequence<String>) -> T) =
    Path("src/main/$name", "input").useLines(block = block)

fun <T> List<T>.allCombinations(): Sequence<Pair<T, T>> {
    return sequence { forEachIndexed { i, a -> subList(i + 1, size).forEach { yield(Pair(a, it)) } } }
}

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
