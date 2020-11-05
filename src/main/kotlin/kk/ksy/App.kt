package kk.ksy

fun main() {
    val hello = "hello world" // immutable
    val warn = null
    val num: Int = 42
    val double: Double = 42.195
    val arr: Array<String> = arrayOf("serq", "asdsd")
    var welcome = "asdf"
    welcome = "!!!"

    System.out.println("hello world by Java, $hello, $warn. $num")
    println("hello world !!!!, $double, $arr, $welcome")

    println(start())

    val numbers = listOf(1, 2, 3, 4, 5)

    println(numbers)
    println(numbers.joinToString())
    println(numbers.joinToString(separator = "*", prefix = "!", postfix = "@"))

    println(max())
    println(max(10, 4))
}

fun start(): String = "OK"

fun max() = 1
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}