package com.kotlin.chap02.section2

fun main() {
    var str1: String = "Hello"
    var str2 = "World"
    var str3 = "Hello"

    // 표현식을 표기할때는 중괄호를 사용한다!
    println("str1 === str2: ${str1 === str2}")
    println("str1 === str2: ${str1 === str3}")

    str1 = "test2"

    println(str1)
    println(str3)
}