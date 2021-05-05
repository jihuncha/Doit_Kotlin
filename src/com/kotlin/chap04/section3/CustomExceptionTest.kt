package com.kotlin.chap04.section3

class InvalidNameException(message: String) : Exception(message)    //사용자가 정의한 예외 클라스

fun main() {
    var name = "Kildong123" //숫자가 포함된 이름

    try {
        validateName(name)
    } catch (e : InvalidNameException) {    // 숫자가 포함된 예외처리
        println(e.message)
    } catch (e: Exception) {    //기타 예외처리
        println(e.message)
    }


}

fun validateName(name: String) {
    if (name.matches(Regex(".*\\d+.*"))) {
        throw InvalidNameException("Your name : $name : contains numerals")
    }
}