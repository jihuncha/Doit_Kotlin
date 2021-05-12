package com.kotlin.chap05.section5.privatetest

private class PrivateClass {
    private var i = 1
    private fun privateFunc() {
        i += 1  //접근 허용
    }
    fun access() {
        privateFunc() // 접근 허용
    }
}

class otherClass {
//    val opc = PrivateClass()    //접근 불가
    fun test() {
        val pc = PrivateClass() //접근 가능
    }
}

fun main() {
    val pc = PrivateClass() //생성 가능
//    pc.i        // 접근 불가능
//    pc.privateFunc()    //접근 불가

}

fun TopFunction() {
    val tpc = PrivateClass()    //객체 생성 가능
}

