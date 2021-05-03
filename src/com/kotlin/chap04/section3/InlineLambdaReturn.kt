package com.kotlin.chap04.section3

fun main() {
    retFunc()
}

private inline fun inlineLambda(a: Int, b:Int, out: (Int, Int) -> Unit) {
    out(a, b)
}

private fun retFunc() {
    println("start of retFunc")               //1
    inlineLambda(10 ,3) { a,b ->        //2
        val result = a + b
        if(result > 10) return              //3 10보다 크면 이 함수를 빠져 나간다. -> 함수자체를 빠져나감
        println("result : $result")         //4 10보다 크면 이 문장에 도달 못함
    }
    println("end of retFunc")               //5

    //출력 : 10보다 큰 경우는     println("start of retFunc")  만 호출된다..
}