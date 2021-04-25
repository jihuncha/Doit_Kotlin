package com.kotlin.chap03.section3

fun main() {
    var result: Int
    val multi = {x:Int, y:Int -> x * y}
    result = multi(10, 20)
    println(result) //200
    println(multi) //(kotlin.Int, kotlin.Int) -> kotlin.Int
}