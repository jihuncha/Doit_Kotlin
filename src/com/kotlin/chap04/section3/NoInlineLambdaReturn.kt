package com.kotlin.chap04.section3

fun main() {
    retFunc()
}

private inline fun inlineLambda(a: Int, b:Int, out: (Int, Int) -> Unit) {
    out(a, b)
}

//private fun retFunc() {
//    println("start of retFunc")
//    inlineLambda(10 ,3) lit@{ a,b ->        //1 람다식 블록의 시작 부분에 라벨을 지정
//        val result = a + b
//        if(result > 10) return@lit              //2 라벨을 사용한 블록의 끝부분 반환
//        println("result : $result")
//    }
//    println("end of retFunc")                   //라벨을 사용하여 이 부분이 실행된다.
//
//    //출력 : start of retFunc
//    //      end of retFunc
//}

//private fun retFunc() {
//    println("start of retFunc")
//    inlineLambda(10 ,3){ a,b ->        //1 람다식 블록의 시작 부분에 라벨을 지정
//        val result = a + b
//        if(result > 10) return@inlineLambda              //2 라벨을 사용한 블록의 끝부분 반환
//        println("result : $result")
//    }
//    println("end of retFunc")                   //라벨을 사용하여 이 부분이 실행된다.
//
//    //출력 : start of retFunc
//    //      end of retFunc
//}

private fun retFunc() {
    println("start of retFunc")
    inlineLambda(10 ,3, fun (a,b) {
        val result = a + b
        if(result > 10) return
        println("result : $result")
    })
    println("end of retFunc")

    //출력 : start of retFunc
    //      end of retFunc
}