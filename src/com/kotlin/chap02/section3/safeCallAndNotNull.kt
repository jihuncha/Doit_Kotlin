package com.kotlin.chap02.section3

fun main() {
    var str1: String? = "Hello"
    str1 = null

    //error!!
//    println("str1 : $str1 , length: ${str1.length}")

    //safe call -> null이 아니면 length / null이면 null 반환
//    println("str1 : $str1 , length: ${str1?.length}")

    //non=null 단정기호 -> null 검사 없이 진행 -> NPE발생
//    println("str1 : $str1 , length: ${str1!!.length}")

    //조건문 사용
    var len = if(str1 != null) str1.length else -1
    println("str1 : $str1 , length: $len")
}