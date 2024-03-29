package com.kotlin.chap06.section2

// lateinit 사용의 예
class Person {
    lateinit var name: String

    fun test() {
        //::은 프로퍼티 참조를 위해 사용
        //! 은 not 의 의미
        if(!::name.isInitialized) { // 프로퍼티의 초기화 여부 판단
            println("not initialized")
        } else {
            println("initialized")
        }
    }
}

fun main() {
    val kildong = Person()
    kildong.test()
    kildong.name = "Kildong" // 이 시점에서 초기화가 된다. (지연초기화)
    kildong.test()
    println("name = ${kildong.name}")
}