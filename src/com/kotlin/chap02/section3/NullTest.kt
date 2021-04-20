package com.kotlin.chap02.section3

fun main() {
    var str1 : String = "Hello Kotlin"
    // 널자체를 허용하지 않음
//    str1 = null

    var str2 : String? = "Hello Kotlin"
    // ? 붙음으로 null 허용
    str2 = null

    println(str2)
}