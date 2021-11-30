import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.useLines

/**
 * Reads lines from the given input txt file and transforms the input
 */
fun <T> readInput(name: String, block: (Sequence<String>) -> T) = Path("src/main/$name", "$name.txt").useLines(block = block)

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
