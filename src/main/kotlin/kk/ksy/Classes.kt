package kk.ksy

fun main() {
    InitOrderDemo("hello")
}

object JustSingleton {
    const val value: String = "Just a value"
}

class Empty

class Classes constructor(name: String)

class Book(name: String)
class Member(
    val id: String,
    val email: String,
    var phone: String, // trailing comma
) {

}

class InitOrderDemo(name: String) {
    val firstProperty = "First Property: $name".also(::println)

    init {
        println("First initializer block that prints $name")
    }

    val secondProperty = "Second property: ${name.length}".also(::println)

    init {
        println("Second initializer block that prints ${name.length}")
    }
}