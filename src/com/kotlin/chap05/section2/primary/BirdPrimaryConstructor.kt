package com.kotlin.chap05.section2.primary

class Bird(var name: String, var wing: Int, var beak: String, var color:String) {
    // 프로퍼티는 매개변수 안에 var를 이용해 프로퍼티로서 선언되어 본문에서 생략함

    fun fly() = println("Fly wing: $wing")
    fun sing(vol:Int) = println("Sing vol:$vol")
}

fun main() {
    val coco = Bird("mybird", 2, "short", "blue")

    coco.color = "yellow"
    println("coco.color : ${coco.color}")
    coco.fly()
    coco.sing(3)
}