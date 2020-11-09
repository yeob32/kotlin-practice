package kk.ksy

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

}

//getters (and setters in case of vars) for all properties
//equals()
//hashCode()
//toString()
//copy()
//component1(), component2(), …, for all properties (see Data classes)
data class Customer(val name: String, val email: String)