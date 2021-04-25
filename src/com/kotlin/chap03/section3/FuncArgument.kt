package com.kotlin.chap03.section3

fun main() {
    val res1 = sum(3,2)
    val res2 = mul(sum(3,3), 3)

    println("res1: $res1, rest2: $res2")
}

private fun sum(a:Int, b:Int) = a+b
fun mul(a:Int, b:Int) = a*b