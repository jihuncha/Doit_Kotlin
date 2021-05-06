package com.kotlin.chap05.section2

class Bird {
    // 1 프로퍼티 - 선어만 함
    var name: String
    var wing: Int
    var beak: String
    var color: String
    
    // 2 부 생성자 - 매개변수를 통해 초기화할 프로퍼티에 지정
    constructor(_name:String, wing: Int, beak:String, color:String) {
        //this를 사용하고 싶지 않다면 _(언더스코어) 사용
        name = _name
//        this.name = name
        this.wing = wing
        this.beak = beak
        this.color = color
    }

    //메서드
    fun fly() = println("Fly wing: $wing")
    fun sing(vol: Int) = println("Sing vol: $vol")
}

fun main() {
    val coco = Bird("mybird", 2, "short","blue")    //3 생성자의 인자로 객체 생성과 동시에 초기화
    
    coco.color = "yellow"
    println("coco.color: ${coco.color}")
    coco.fly()
    coco.sing(3)
}

//coco.color: yellow
//Fly wing: 2
//Sing vol: 3