# Doit_Kotlin


# 2. 변수와 자료형 연산자.
* val - 변경 불가능
* var - 변경 가능

~~~kotlin
val username : String = "Kildong"
~~~

* 타입 추론이 가능 
~~~kotlin
val username = "Kildong"
~~~

* 코틀린 자료형은 참조형 자료형을 사용

* 표현식은 {$} (중괄호) 로 , 변수는 $로

* 다중 문자열 처리
~~~kotlin
    // 다중 문자열 처리
    val formattedString = """
       var a = 6
       var b = "Kotlin"
       println(a + num)
    """
~~~

* typealias 로 별명 붙이기
~~~kotlin
typealias Username = String

fun main() {
    //username 이 string
    var user:Username = "Kildong"
}
~~~

### 2-3. 자료형 검사하고 반환하기

* null 허용하도록할려면 자료형에 ? 붙이기

~~~kotlin
    var str1 : String = "Hello Kotlin"
    // 널자체를 허용하지 않음
    str1 = null  //error!!

    var str2 : String? = "Hello Kotlin"
    // ? 붙음으로 null 허용
    str2 = null

    println(str2)  // null print된다.
~~~

* 세이프 콜과 non-null 단정 기호
~~~kotlin
    var str1: String? = "Hello"
    str1 = null

    //error!!
    //    println("str1 : $str1 , length: ${str1.length}")

    //safe call -> null이 아니면 length / null이면 null 반환
    //    println("str1 : $str1 , length: ${str1?.length}")

    //non=null 단정기호 -> null 검사 없이 진행 -> NPE발생
    println("str1 : $str1 , length: ${str1!!.length}")

    //조건문 사용
    var len = if(str1 != null) str1.length else -1
    println("str1 : $str1 , length: $len")
~~~

* 엘비스 연산자 : 변수가 null인지 검사하여 null이 아니면 왼쪽 / null이면 오른쪽 식을 실행

~~~ kotlin
    var str1: String? = "fdsfa"
    str1 = null
    println("str1 : $str1 length: ${str1?.length ?: -1}")
    
        //같은 식
//    if (str1 != null) str1.length else -1
~~~

* 자료형 변환 : 기본형과 참조형 자료형 비교 원리 이해하기
~~~ kotlin
    val a: Int = 128
    val b = a
    println(a === b)

    val c: Int? = a
    val d: Int? = a
    val e: Int? = c

    println(c == d) //값은 동일
    println(c === d) // 값은 동일하나 참조하는 곳이 다르다
    println(c === e) // 참조하는 곳과 값이 같다.

    //형 변환 test
    val test : Double = a.toDouble()
    println(test)
~~~

* 스마트 캐스트 : 컴파일러가 자동 형변환 
* 자료형 검사 : is 사용
* Any 자료형 : 최상위 기본 클래스

~~~kotlin
    var test: Number = 12.2

    println("$test")

    test = 12
    println("$test")

    test = 120L
    println("$test")

    test += 12.0f
    println("$test")

    // 자료형 검사
    println(test is Int)
    println(test !is Int)

    // Any 자료형 -> 최상위 기본 클래스, 자료형을 결정하지 않아도 선언 가능
    val x :Any
    x = "Hello"
    if (x is String){
        print(x.length)
    }
~~~

* Any형 변수의 변환
~~~ kotlin
    var a: Any = 1
    a = 20L
    println("a: $a type: ${a.javaClass}") // a 의 자바 기본형을 출력하면 long이 나옴
~~~
~~~ kotlin
    fun main() {
        checkArg("Hello")
        checkArg(5)
    }

    fun checkArg(x: Any) {
        println("${x.javaClass} type!!")
    }
~~~