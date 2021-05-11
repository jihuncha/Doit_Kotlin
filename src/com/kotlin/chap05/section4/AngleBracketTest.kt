package com.kotlin.chap05.section4

open class A {
    open fun f() = println("A Class f()")
    fun a( ) = println("A Class a()")
}

interface B {
    fun f( ) = println("B interface f( )")  //인터페이스는 기본적으로 open임
    fun b( ) = println("B interface b( )")
}

class C: A( ), B {  //쉼표를 사용해 클래스와 인터페이스를 지정
    // 컴파일되려면 f( )가 오버라이딩 되어야 한다.
    override fun f( ) = println("C Class f( )")

    fun test( ) {
        f() //현재 클래스의
        b() //인터페이스의
        super<A>.f()    //A 클래스의
        super<B>.f()    //B 클래스의
    }
}

fun main() {
    val c = C( )
    c.test()
}

/*
C Class f( )
B interface b( )
A Class f()
B interface f( )*/
