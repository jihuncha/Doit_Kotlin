package com.kotlin.chap05.section4.personthis

open class Person {
    constructor(firstName:String) {
        println("[Person] firstName: $firstName")
    }

    constructor(firstName:String, age:Int) {    //3. 먼저 출력된다.
        println("[Person] firstName: $firstName, $age")
    }
}

class Developer: Person {
    constructor(firstName: String): this(firstName, 10) {   //1. 처음에 여기 this 호출
        println("[Developer] $firstName")
    }

    constructor(firstName: String, age:Int): super(firstName, age) {    //2.super로 이동
        println("[Developer] $firstName")
    }
}

fun main() {
    val sean = Developer("Sean")
}

/*
*
[Person] firstName: Sean, 10
[Developer] Sean
[Developer] Sean
*
* */