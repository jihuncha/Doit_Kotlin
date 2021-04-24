package com.kotlin.chap03.section1

fun main() {
    namedParam(x = 200, z = 100) // x,z의 이름과 함수를 같이 호출
    namedParam(z = 150) // z의 이름과 함께 호출
}

fun namedParam(x: Int = 200, y: Int=200, z:Int){
    println(x+y+z)
}