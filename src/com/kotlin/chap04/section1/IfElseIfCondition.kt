package com.kotlin.chap04.section1

fun main() {
    print("Enter the score: ")
    val score = readLine()!!.toDouble() //콘솔로부터 입력받는다 / !! not-null 사용
    var grade: Char = 'F'

//    if (score >= 90.0) {
//        grade = 'A'
//    } else if (score >= 80.0 && score <= 89.9) {
//        grade = 'B'
//    } else if (score >= 70.0 && score <= 79.9) {
//        grade = 'C'
//    }

    //변수 이름 in 시작값..마지막값으로 범위 표현 가능
    if (score >= 90.0) {
        grade = 'A'
    } else if (score in 80.0..89.9) {
        grade = 'B'
    } else if (score in 70.0..79.9) {
        grade = 'C'
    }

    println("Score : $score, Grade: $grade")
}