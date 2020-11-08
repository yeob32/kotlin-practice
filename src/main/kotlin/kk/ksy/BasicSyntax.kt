package kk.ksy

import java.util.concurrent.ConcurrentHashMap

fun main() {
    val hello = "hello world"
    val warn = null
    val num: Int = 42
    val double: Double = 42.195
    val arr: Array<String> = arrayOf("serq", "asdsd")
    var welcome = "asdf"
    welcome = "!!!"

    if (welcome != null) {
        val welcomeKotlin = welcome.length
        println("welcome $welcomeKotlin")
    }

    val nullableString: String? = null
    println("print ${nullableString?.toInt()}")
    println("print ${nullableString?.toInt() == null}")
    println("print ${nullableString?.toInt() ?: 369}")

    // StringUtils.isEmpty(some)
    welcome.isNullOrEmpty()

    System.out.println("hello world by Java, $hello, $warn. $num")
    println("hello world !!!!, $double, $arr, $welcome")

    fun start(): String = "OK"
    println(start())

    val numbers = listOf(1, 2, 3, 4, 5)

    println(numbers)
    println(numbers.joinToString())
    println(numbers.joinToString(separator = "*", prefix = "!", postfix = "@"))

    fun max() = 1
    fun maxOf(a: Int, b: Int) = if (a > b) a else b
    fun max(a: Int, b: Int): Int {
        return if (a > b) a else b
    }

    println(max())
    println(max(10, 4))

    fun printSum(a: Int, b: Int): Unit { // Unit 생략 가능
        println("sum $a and $b is ${a + b}")
    }
    printSum(1, 2)

    var a = 1
    val s1 = "a is $a"

    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)

    fun parseInt(str: String): Int? {
        return str.toIntOrNull()
    }
    println("${parseInt("")} is maybe null")
    println("${parseInt("")} is maybe null")

    fun getStringLength(obj: Any): Int? {
        if (obj is String) {
            return obj.length;
        }

        return null
    }

    fun printLength(obj: Any) {
        // ?: ->
        println("'$obj' string length is ${getStringLength(obj) ?: "... err, not a string"} ")
    }

    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))

    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) println(item)
    for (index in items.indices) println("item at $index is ${items[index]}")

    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

    fun describe(obj: Any): String =
        when (obj) {
            1 -> "One"
            "Hello" -> "Greeting"
            is Long -> "Long"
            !is String -> "Not a string"
            else -> "Unknown"
        }

    println(describe(1))
    println(describe(10L))
    println(describe("Hello"))
    println(describe(""))
    println(describe(10.0))

    val x = 10
    val y = 9
    println(x in 1..y + 1)
    if (x in 1..y + 1) {
        println("fits in range")
    }

    val mutableList = mutableListOf("real", "recognize", "real")
    mutableList.add("!!")
    println("mutable $mutableList")

    val list = listOf("a", "b", "c")
    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }

    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }

    for (x in 1..5) {
        print(x)
    }

    for (x in 1..10 step 2) print(x)
    for (x in 9 downTo 0 step 3) print(x)

    for (item in items) {
        println(item)
    }

    val itemsWithSet = setOf("apple", "banana", "kiwifruit")
    when {
        "orange" in itemsWithSet -> println("juicy")
        "apple" in itemsWithSet -> println("apple is fine too")
    }

    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }

    println(emptyList<String>())

    val v1 = "v1";
    val v2 = "v2";

    // v1.equals(v2)
    println(v1 == v2)

    val key = "someKey"
    val map: ConcurrentHashMap<String, Int> = ConcurrentHashMap()
    val value = map.getOrPut(key) {
        1
    }

    listOf(3, 7).maxOrNull()
    val list1 = listOf<Int>()
    val maxValue = list1.maxOrNull()
    // compile error
    // val sumValue = maxValue * maxValue
    if(maxValue != null) {
        val sumValue = maxValue * maxValue
    }

    // to Int , T? -> T
    // 단, 값이 null 일 경우 NullPointerException()
    val list2 = listOf(5)
    val maxValue2 = list2.maxOrNull()!!
    val sumValue2 = maxValue2 * maxValue2
}





