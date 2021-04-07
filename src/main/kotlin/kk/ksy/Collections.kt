package kk.ksy

import java.util.stream.Collectors

/**
 * Collections / Sequences
 *
 * Java 8 부터는 Stream 제공하고 Lazy evaluation 으로 동작한다.
 *
 * kotlin Collections 은 기본적으로 Functional functions 제공하며,
 * Eager evaluation 으로 동작한다.
 *
 * Sequences 사용하면 Java Stream 처럼 Lazy evaluation 으로 동작한다.
 *
 * 리스트의 데이터가 많다면 Sequences 를 사용하여 Lazy evaluation 으로 동작하여 성능 개선
 *
 * * Reference
 * https://codechacha.com/ko/kotlin-sequences/
 * https://kotlinlang.org/docs/sequences.html#from-chunks
 */

val fruits = listOf("one", "two", "three", "four")

fun main(args: Array<String>) {
    println("===== 코틀린 Collections 예제 !! =====")
    fruits
        .filter { println("checking the length of $it"); it.length > 5 }
        .map { println("mapping to the length of $it"); it.length }
        .toList()

    println("===== 코틀린 Stream 예제 !! =====")
    // 코틀린에서 스트림 사용 가능
    fruits.stream()
        .filter { name: String ->
            println("checking the length of $name")
            name.length > 5
        }
        .map { name: String ->
            println("mapping to the length of $name")
            "" + name.length
        }
        .collect(Collectors.toList())

    println("===== 코틀린 Sequences 예제 !! =====")
    fruits.asSequence()
        .filter { println("checking the length of $it"); it.length > 5 }
        .map { println("mapping to the length of $it"); "${it.length}" }
        .toList()

    println("===== 코틀린 Collections Second 예제 !! =====")
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
    val lengthsList = words
        .filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(5)

    println("Lengths of first 4 words longer than 3 chars:")
    println(lengthsList)

    println("===== 코틀린 Sequences Second 예제 !! =====")
    //convert the List to a Sequence
    val wordsSequence = words.asSequence()
    val lengthsSequence = wordsSequence
        .filter { println("filter: $it"); it.length > 3 }
        .map { println("length: ${it.length}"); it.length }
        .take(4)

    println("Lengths of first 4 words longer than 3 chars")
    // terminal operation: obtaining the result as a List
    println(lengthsSequence.toList())
}