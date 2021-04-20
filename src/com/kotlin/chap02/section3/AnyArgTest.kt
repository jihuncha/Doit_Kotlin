package com.kotlin.chap02.section3

fun main() {
    checkArg("Hello")
    checkArg(5)
}

fun checkArg(x: Any){
    println("${x.javaClass} type!!")
}