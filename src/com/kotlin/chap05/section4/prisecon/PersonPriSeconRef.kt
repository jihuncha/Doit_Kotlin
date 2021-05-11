package com.kotlin.chap05.section4.prisecon

class Person(firstName: String, out:Unit = println("[Primary Constructor] Paramerter")) {   //주생성자
    val fName = println("[Property] Person fName: $firstName")  //프로퍼티 할당

    init {
        println("[init] Person init block") //초기화 블록
    }

    //부 생성자
    constructor(firstName: String, age:Int, out: Unit = println("[Secondary Constructor] Paramerter")): this(firstName) {
        println("[Secondary Constructor] Body: $firstName, $age")   //부 생성자 본문
    }
}

fun main() {
    val p1 = Person("Kildong", 30)
    println()
    val p2 = Person("Dooly")
}

/*
* [Secondary Constructor] Paramerter
[Primary Constructor] Paramerter
[Property] Person fName: Kildong
[init] Person init block
[Secondary Constructor] Body: Kildong, 30

[Primary Constructor] Paramerter
[Property] Person fName: Dooly
[init] Person init block
*
* */