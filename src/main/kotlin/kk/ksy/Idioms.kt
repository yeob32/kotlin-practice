package kk.ksy

fun main() {

    fun foo(a: Int = 0, b: String = "") = println("$a, $b")

    val positives = listOf(10, 7, 0, -1).filter { x -> x > 0 }
    println(positives)
    val positivesShorter = listOf(10, 7, 0, -1).filter { it > 0 }
    println(positivesShorter)
}

//getters (and setters in case of vars) for all properties
//equals()
//hashCode()
//toString()
//copy()
//component1(), component2(), …, for all properties (see Data classes)
data class Customer(val name: String, val email: String)