package com.kotlin.chap04.section2

fun main() {
    print("Enter the lines:")
    val n = readLine()!!.toInt()

    for (line in 1..n){
        for (space in 1..(n-line)) print(" ") //공백 출력
        for (star in 1..(2* line -1)) print("*") //별표 출력
        println() //개행
    }
}