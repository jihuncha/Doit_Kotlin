@file:JvmName("PKLevel")        //접근할 클래스 이름 변경
package com.kotlin.chap06.section3

// 패키지 레벨 함수 혹은 최상위 함수라고 함
fun packageLevelFunc() {
    println("Package-Level Function")
}

fun main() {
    packageLevelFunc()
}

//Package-Level Function
