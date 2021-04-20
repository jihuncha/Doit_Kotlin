package com.kotlin.chap02.section3

fun main() {
    var str1: String? = "fdsfa"
    str1 = null
    println("str1 : $str1 length: ${str1?.length ?: -1}")

    //같은 식
//    if (str1 != null) str1.length else -1
}