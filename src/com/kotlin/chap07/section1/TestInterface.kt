package com.kotlin.chap07.section1

interface Temp_a {
    fun functionA(){}
}

interface Temp_b {
    fun functionB(){}
}

class Temp_c(val a:Temp_a, val b:Temp_b) {
    fun function() {
        a.functionA()
        b.functionB()
    }
}

//위임 사용
class DelegatedC(a:Temp_a, b:Temp_b): Temp_a by a, Temp_b by b {
    fun function() {
        functionA()
        functionB()
    }
}

//////////////////////////////////////////////////////////////////////////////////////////

interface Nameable {
    var name: String
}

class StaffName: Nameable{
    override var name: String = "Sean"
}

class Work: Runnable {
    override fun run() {
        println("work....")
    }
}

//각 매개변수에 해당 인터페이스를 위임
class Person(name:Nameable, work:Runnable): Nameable by name, Runnable by work

fun main() {
    val person = Person(StaffName(), Work())    //생성자를 사용해 객체 바로 전달
    println(person.name)    //여기서 StaffName클래스의 name접근
    person.run()            // Work 클래스 run 접근
}