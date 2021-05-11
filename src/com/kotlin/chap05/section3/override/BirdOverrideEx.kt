package com.kotlin.chap05.section3.override

// 상속 간으한 클래스를 위해 open 사용
open class Bird(var name:String, var wing: Int, var beak:String, var color:String) {
    // 메서드
    fun fly( ) = println("Fly wing: $wing")
    open fun sing(vol: Int) = println("Sing vol: $vol")
}

class Parrot(name: String, wing: Int = 2, beak: String, color: String, var language:String = "natural") : Bird(name,wing, beak, color) {
    fun speak() = println("Speak! $language")   // parrot에 추가된 메서드
    override fun sing(vol: Int) {   //오버 라이딩된 메서드
        println("I'm a parrot! The volume level is $vol")
        speak() //달라진 내용
    }
}

fun main() {
    val parrot = Parrot(name="myparrot", beak = "short", color = "multiple")
    parrot.language = "English"
    
    println("Parrot : ${parrot.name},  ${parrot.wing}, ${parrot.beak}, ${parrot.color}, ${parrot.language}")
    parrot.sing(5)  //달라진 메서드
    
}

/*
* Parrot : myparrot,  2, short, multiple, English
I'm a parrot! The volume level is 5
Speak! English
* */