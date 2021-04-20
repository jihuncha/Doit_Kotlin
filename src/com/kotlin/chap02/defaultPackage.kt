package com.kotlin.chap02

import java.lang.Math.PI
import java.lang.Math.abs

fun main() {
    val intro: String = "안녕하세요!"
    val num : Int = 20

    println(PI)
    println(abs(-12.6))

    println("Intro: $intro, num: $num")

    var num_2: Double = 0.1

    for(x in 0..999) {
        num_2 += 0.1
    }

    println(num_2)
}