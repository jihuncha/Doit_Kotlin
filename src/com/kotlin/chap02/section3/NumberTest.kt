package com.kotlin.chap02.section3

fun main() {
    var test: Number = 12.2

    println("$test")

    test = 12
    println("$test")

    test = 120L
    println("$test")

    test += 12.0f
    println("$test")

    // 자료형 검사
    println(test is Int)
    println(test !is Int)

    // Any 자료형 -> 최상위 기본 클래스, 자료형을 결정하지 않아도 선언 가능
    val x :Any
    x = "Hello"
    if (x is String){
        println(x.length)
    }

    // as에 의한 스마트 캐스트
    val y = "123"
    val temp:String = y as String

    print(temp is String)

}