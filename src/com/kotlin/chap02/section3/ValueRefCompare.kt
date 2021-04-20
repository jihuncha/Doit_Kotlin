package com.kotlin.chap02.section3

fun main() {

    val a: Int = 128
    val b = a
    println(a === b)

    val c: Int? = a
    val d: Int? = a
    val e: Int? = c

    println(c == d) //값은 동일
    println(c === d) // 값은 동일하나 참조하는 곳이 다르다
    println(c === e) // 참조하는 곳과 값이 같다.

    //형 변환 test
    val test : Double = a.toDouble()
    println(test)
}