package com.kotlin.chap02.section2

// 표현식을 표기할때는 중괄호를 사용한다!
fun main() {
    var a = 1
    var str1 = "a = $a"
    var str2 = "a = ${a + 2}"

    println("str1 : \"$str1\" , str2 : \"$str2\"")
}