package com.kotlin.chap05.section1

class Bird {
    var name : String = "myBird"
    var wing : Int = 2
    var color : String = "blue"

    fun fly() = println("Fly~~")
    fun sing(vol:Int) = println("Sing vol: $vol")
}

fun main() {
    val coco = Bird()
    coco.color = "red"

    println("coco.color : ${coco.color}")
    coco.fly()
    coco.sing(100)
}

//coco.color : red
//Fly~~
//Sing vol: 100
