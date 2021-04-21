package com.kotlin.chap02.section4

fun main() {
    val number1 = 5
    val number2 = -5


    val temp = number1 shr 1
    val temp2 = number1 ushr 1
    val temp3 = number2 shr 1
    val temp4 = number2 ushr 1

    println(number1 shr 1) //2 (0101) -> (0010)
    println(number1 ushr 1) //
    println(number2 shr 1) // 1111 1111 1111 ..... 1111 0101 -> 맨뒤가 0110
    println(number2 ushr 1) //책 참고
}