package com.kotlin.chap03.section5

fun main() {
    shortFunc_second(3) { println("First call: $it")}
}

inline fun shortFunc_second(a:Int, noinline out:(Int) -> Unit) {
    println("Before calling out()")
    out(a)
    println("After calling out()")
}