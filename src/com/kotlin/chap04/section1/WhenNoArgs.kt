package com.kotlin.chap04.section1

fun main() {
    print("Enter the Score:")

    val score = readLine()!!.toDouble()
    var grade: Char = 'F'

    //인자가 없음
    when {
        score >= 90.0 -> grade = 'A'   //인자 있는 when 문과 다르게 조건식을 구성 가능
        score in 80.0..89.9 -> grade = 'B'
        score in 70.0..79.9 -> grade = 'C'
        score < 70.0 -> grade = 'F'
    }

    println("Score: $score, Grade: $grade")
}