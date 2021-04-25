package com.kotlin.chap03.section3

fun main() {
    println("funcfunc : ${funcFunc()}")
}

private fun sum(a: Int, b: Int) = a+b

fun funcFunc():Int {
    return sum(2,2)
}