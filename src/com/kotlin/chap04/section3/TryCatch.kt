package com.kotlin.chap04.section3

fun main() {
    val a = 6
    val b = 0
    val c : Int

    try {
        c = a / b       //0으로 나눔
    } catch (e: Exception) {
        println("Exception is handled")
    } finally {
        println("finally 블록은 반드시 실행된다.")
    }
}