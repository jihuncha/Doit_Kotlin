package com.kotlin.chap03.section3

fun main() {
    val out:() -> Unit = {println("Hihi")}

    out()
    val new = out
    new()
}