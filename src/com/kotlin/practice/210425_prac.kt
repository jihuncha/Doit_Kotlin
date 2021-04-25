package com.kotlin.practice

fun main() {

    //생략되지 않은 전체 표현
    val multi1: (Int, Int) -> Int = {x:Int, y:Int -> x * y}
    //선언 자료형 생략
    val multi2 = {x:Int,y:Int -> x*y}
    //람다식 매개변수 자료형의 생략
    val multi3 :(Int,Int) -> Int ={x,y -> x*y}

    val greet:() -> Unit={println("Hello")}
    val square:(Int) -> Int ={x -> x*x}

    val nestedLamda: () -> () -> Unit = {{println("nested")}}
}