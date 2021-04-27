package com.kotlin.practice

fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a+b}")
}

fun main() {
//    printSum(1,2)
    println(highFunc({x,y -> x * y},10,20))
}

private fun highFunc(sum: (Int, Int) -> Int, a:Int, b:Int): Int = sum(a,b)