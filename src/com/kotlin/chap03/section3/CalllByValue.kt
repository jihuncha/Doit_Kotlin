package com.kotlin.chap03.section3

fun main() {
    val result = callByValue(lambda())
    println(result)
}

fun callByValue(b: Boolean):Boolean {
    println("callByValue function")
    return b
}

val lambda: () -> Boolean = { //람다 표현식이 2줄
    println("lambda function")
    true // 마지막 표현식 문장의 결과가 반환
}

//lambda function
//callByValue function
//true