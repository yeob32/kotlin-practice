package kk.ksy

import java.lang.IllegalArgumentException
import java.math.BigDecimal
import java.util.*

fun main() {

    fun foo(a: Int = 0, b: String = "default value") = println("$a, $b")
    foo()

    val positives = listOf(10, 7, 0, -1).filter { x -> x > 0 }
    println(positives)
    val positivesShorter = listOf(10, 7, 0, -1).filter { it > 0 }
    println(positivesShorter)

    val emailList = listOf("sykim@gmail.com", "ksyy1201@gmail.com")
    if ("sykim@gmail.com" in emailList) println("exist email !!")
    if ("sykim@gmail.com1" !in emailList) println("not exist email !!")

    val map = mutableMapOf<String, Any>(Pair("v1", 10), Pair("v2", 20), Pair("v3", 30))
    map["v1"] = 10000
    println(map["v1"])

    val map1 = mapOf<String, Any>("v1" to 10, "v2" to 20, "v3" to 30)
    for ((k, v) in map1) println("key : $k, value : $v")

    for (i in 1..10) print("$i>"); println("")
    for (i in 1 until 10) print("$i>"); println("")
    for (i in 2..10 step 2) print("$i>"); println("")
    for (i in 10 downTo 1) print("$i>"); println("")

    val p: String by lazy {
        println("lazy load p !!")
        "lazy hello"
    }
    println(p)

    // Extension Functions
    fun Person.spaceToCamelCase(str: String): String {
        return str
    }

    val person = Person("sykim")
    person.spaceToCamelCase("sdsds")


    val values = mapOf("k1" to "v1")
    try {
        values["hi"] ?: throw IllegalStateException("Email is missing!")
    } catch (e: IllegalStateException) {
        println("ERROR : ${e.message}")
    }

    val value1: String? = "what is let ?"
    value1?.let { println(it) }

    val values2 = listOf("apple", null, "banana")
    for (value in values2) {
        value?.let { println(it) }
    }

    fun transform(color: String): Int {
        return when (color) {
            "Red" -> 0
            "Green" -> 1
            "Blue" -> 2
            else -> throw IllegalArgumentException("Invalid color param value")
        }
    }

    transform("Red")

    val value = try {
        "hi! hi!"
    } catch (e: Exception) {
        println(e)
    }

    println("try value : $value")

    fun foo(param: Int): String {
        val value = if (param == 1) {
            "one"
        } else if (param == 2) {
            "two"
        } else {
            "three"
        }

        return value
    }
    println("foo : ${foo(3)}")

    fun arrayOfMinusOnes(size: Int): IntArray {
        return IntArray(size).apply { fill(-1) }
    }

    println(arrayOfMinusOnes(10).contentToString())

    val myRectangle = Rectangle().apply {
        length = 4
        breadth = 5
        color = 0xFAFAFA
    }

    println(myRectangle.length)

    fun calcTaxes(): BigDecimal = TODO("이야 이건 또 뭐지")

    var a = 1
    var b = 2
    a = b.also { b = a }

    println("a : $a")
}

class Rectangle {
    var length: Int = 0
    var breadth: Int = 0
    var color: Int = 0
}

class Person(val name: String) {

}

// Singleton
object Resource {
    const val name = "name"
}

//getters (and setters in case of vars) for all properties
//equals()
//hashCode()
//toString()
//copy()
//component1(), component2(), …, for all properties (see Data classes)
data class Customer(val name: String, val email: String)
