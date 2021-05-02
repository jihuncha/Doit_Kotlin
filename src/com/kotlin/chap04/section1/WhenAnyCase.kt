package com.kotlin.chap04.section1

fun main() {
    cases("Hello")      //String
    cases(1)            //Int
    cases(System.currentTimeMillis())   // Long
    cases(temp())                       // Not a String
}

fun cases(obj: Any) {
    when (obj) {
        1 -> println("Int: $obj")
        "Hello" -> println("String: $obj")
        is Long -> println("Long: $obj")
        !is String -> println("Not a String")
        else -> println("Unknown")
    }
}

public class temp(){

}