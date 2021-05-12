package com.kotlin.chap05.section5.internal

internal class InternalClass {
    internal var i = 1
    internal fun icFunc() {
        i += 1  //접근 허용
    }
    fun access() {
        icFunc()    //접근 허용
    }
}

class Other {
    internal val ic = InternalClass()   //프로퍼티를 지정할떄 internal로 맞춰야함
    fun test() {
        ic.i        //접근가능
        ic.icFunc() //접근가능
    }
}

fun main() {
    val mic = InternalClass()
    mic.i
    mic.icFunc()            //생성 및 접근 모두 가능
}