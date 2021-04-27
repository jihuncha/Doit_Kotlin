package com.kotlin.practice

//fun main() {
//    println(highFunc({x,y -> x * y},10,20))
//}
//
//private fun highFunc(sum: (Int, Int) -> Int, a:Int, b:Int): Int = sum(a,b)

fun main() {
    val res1 = sum(3,2)
    val res2 = mul(sum(3,3), 3)

    println("res1: $res1, rest2: $res2") //5 18
}

fun sum(a:Int, b:Int) = a+b
fun mul(a:Int, b:Int) = a*b