# Doit_Kotlin


# 2. 변수와 자료형 연산자
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
        checkArg("Hello") //String
        checkArg(5)       //Int
    }

    fun checkArg(x: Any) {
        println("${x.javaClass} type!!")
    }
~~~

### 2-4. 코틀린 연산자

* 기본적인 연산자는 JAVA와 비슷하다.

1. 산술 연산자
   
2. 대입 연산자
   
3. 증가 감소 연산자
~~~ kotlin
    var num1 = 10
    var num2 = 10

    // 연산자 수행 이전에
    val result1 = ++num1
    //연산자 수행 이후에
    val result2 = num2++

    println("result1 : $result1") //11
    println("result2 : $result2") //10
~~~

4. 비교 연산자
   
   === !== // 참조연산자 (주소까지 같은지 판별)

5. 논리 연산자
   
6. 비트 연산자

6-1. 비트 메서드

* 부호 비트는 이동시키지 않으면서 사라진 비트의 값을 0으로 채운다.

![비트연산자.png](src/com/kotlin/image/bit_operator_1.png)

~~~kotlin
fun main() {
    var x = 4
    var y = 0b0000_1010 //10진수 5
    var z = 0x0F //10진수 15

    println("x shl 2 -> ${x shl 2}") // 16
    println("x.inv() -> ${x.inv()}") // -5

    println("y shr 2 -> ${y shr 2}") // 2


}
~~~

6-2. 비트 이동 연산자 ushr
* 잘 이해가 안감..
* 제일 왼쪽 비트에 0을 밀어 넣으면서 오른쪽으로 비트가 이동한다.(부호 비트 포함)

~~~kotlin
fun main() {
    val number1 = 5
    val number2 = -5

    println(number1 shr 1) //2 (0101) -> (0010)
    println(number1 ushr 1) // 왜 2??
    println(number2 shr 1) // 1111 1111 1111 ..... 1111 0101 -> 맨뒤가 0110
    println(number2 ushr 1) //책 참고
}
~~~

6-3. 논리 연산자 or 

6-4. 논리곱 연산자 and

6-5. 배타적합 연산자 xor 

* 베타적합을 이용하여 swap 가능

~~~kotlin
fun main() {
    var number1 = 12
    var number2 = 25

    number1 = number1 xor number2
    number2 = number1 xor number2
    number1 = number1 xor number2

    println("number1 - $number1") //25
    println("number1 - $number2") //12

}
~~~

6-6. 반전연산자 inv

* 비트를 뒤집는다.




# 3. 함수와 함수형 프로그래밍
### 3-1.함수 선언/호출

* 함수 구조

1. 함수 선언할때 -> 매개변수
2. 함수 호출할때 -> 인자
~~~ kotlin
//기본
fun sum(a:Int, b:Int): Int {
    var sum = a + b
    return sum
}

//return 변수 생략
fun sum(a:Int, b:Int): Int {
    return a + b
}

//중괄호 생략
fun sum(a:Int, b:Int): Int = a + b

//변환값 type 생략
fun sum(a:Int, b:Int) = a + b
~~~



* 함수의 호출과 메모리

책 참고. stack에 대한 학습 필요(선입후출)

main() 생성 -> max()생성 -> max()소멸 -> main()소멸

* 반환값이 없는 함수

1. 두 인자를 그대로 출력하는 함수 (반환값이 없을때는 Unit사용) 
   -> java void 와 비슷하나 unit의 경우는 특수한 객체를 반환한다는 차이점이 있다.

~~~kotlin
fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a+b}")
}
~~~

* 매개변수 제대로 활용하기
1. 코틀린은 매개변수의 기본값 기능 제공

~~~kotlin
fun main() {
    val name = "길동씨"
    val email = "kildong@example.kr"

    add(name) //default가 출력된다.
    add(name, email)
    add("하하하하","hohohoho.co.kr")
    defaultArgs() //300
    defaultArgs(200) //400
}

fun add(name:String, email:String = "default") {
    val output = "${name}님의 이메일은${email}입니다"
    println(output)
}

fun defaultArgs(x: Int = 100, y:Int = 200){
    println(x+y)
}

~~~

* 매개변수 이름과 함께 함수 호출하기

~~~kotlin
fun main() {
    namedParam(x = 200, z = 100) // x,z의 이름과 함수를 같이 호출
    namedParam(z = 150) // z의 이름과 함께 호출
}

fun namedParam(x: Int = 200, y: Int=200, z:Int){
    println(x+y+z)
}
~~~

* 매개 변수의 개수가 고정되지 않는 함수 사용하기

인자가 3개,4개인 함수가있는데 동작만 동일하다면 함수를 여러개 만드는 행위는 낭비

#### 코틀린에서는 가변 인자를 사용!!

~~~kotlin
fun main() {
    normalVarargs(1,2,3,4) //1234
    normalVarargs(5,6,7) //567
}

// 가변인자 vararg
fun normalVarargs(vararg counts: Int) {
    for (num in counts) {
        print("$num ")
    }

    print("\n")
}
~~~

### 3-2. 함수형 프로그래밍
* 함수형 프로그래밍은 람다식/고차 함수를 사용

* 순수 함수
-> 부작용이 없는 함수가 함수 외부의 어떤 상태도 바꾸지 않는 경우

~~~kotlin
fun sum(a: Int, b:Int):Int {
    return a+b // 동일한 인자인 a,b를 입력받아 항상 a + b를 출력
}
~~~
* 순수 함수의 조건
1. 같은 인자에 대하여 항상 같은 값을 반환한다.
2. 함수 외부의 어떤 상태도 바꾸지 않는다.

* 람다식
1. 수학에서 람다
   -> 이름이 없는 함수로 2개이상의 입력은 1개의 출력으로 단순화
2. 함수형 프로그래밍의 람다
   -> 다른 함수의 인자로 넘기는 함수, 함수의 결괏값으로 반환하는 함수, 변수에 저장하는 함수

* 일급 객체 
함수형 프로그래밍 에서는 함수를 일급 객체로 생각한다.<br/>

* 일급 객체의 특징
1. 일급 객체는 함수의 인자로 전달할 수 있다.
2. 일급 객체는 함수의 반환 값에 사용할 수 있다.
3. 일급 객체는 변수에 담을 수 있다.

* 고차 함수
고차 함수는 다른 함수를 인자로 사용하거나 함수를 결괏값으로 반환하는 함수를 말합니다.

~~~kotlin
fun main() {
    println(highFunc({x,y -> x * y},10,20))
}

fun highFunc(sum: (Int, Int) -> Int, a:Int, b:Int): Int = sum(a,b)
~~~
   
* 함수형 프로그래밍의 정의와 특징
1. 순수 함수를 사용해야 한다.
2. 람다식을 사용할 수 있다.
3. 고차 함수를 사용할 수 있다.

### 3-3. 고차 함수와 람다식

#### 일반 함수의 고차 함수의 형태

* 일반 함수를 인자나 반환값으로 사용하는 고차 함수

~~~kotlin
//인자로 함수 사용 
fun main() {
    val res1 = sum(3,2)
    val res2 = mul(sum(3,3), 3)

    println("res1: $res1, res2: $res2") //5 18
}

fun sum(a:Int, b:Int) = a+b
fun mul(a:Int, b:Int) = a*b
~~~

~~~kotlin
//반환값으로 함수 사용
fun main() {
    println("funcfunc : ${funcFunc()}") //4
}

private fun sum(a: Int, b: Int) = a+b 

fun funcFunc():Int {
    return sum(2,2)
}
~~~

#### 람다식을 사용하는 고차 함수 형태

~~~kotlin
fun main() {
    var result: Int
    val multi = {x:Int, y:Int -> x * y} //일반 변수에 람다식을 할당
    result = multi(10, 20) // 람다식이 할당된 변수는 함수처럼 사용이 가능하다.
    println(result) //200
    println(multi) //(kotlin.Int, kotlin.Int) -> kotlin.Int
}
~~~

~~~kotlin
  //생략되지 않은 전체 표현
    val multi1: (Int, Int) -> Int = {x:Int, y:Int -> x * y}
    //선언 자료형 생략
    val multi2 = {x:Int,y:Int -> x*y}
    //람다식 매개변수 자료형의 생략
    val multi3 :(Int,Int) -> Int ={x,y -> x*y}

    val greet:() -> Unit={println("Hello")}
    val square:(Int) -> Int ={x -> x*x}
    
    val nestedLamda: () -> () -> Unit = {{println("nested")}}
~~~

* 매개변수에 람다식 함수를 이용한 고차 함수
~~~kotlin
fun main() {
    var result : Int
    result = highOrder({x,y -> x+y}, 10,20)
    println(result)
}

fun highOrder(sum_2:(Int, Int) -> Int, a:Int, b:Int):Int {
    return sum_2(a,b)
}
~~~

* 인자와 반환값이 없는 람다식 함수

~~~kotlin
fun main() {
    //자료형 추론이 가능하므로 val out = {prinln("Hello World")} 같은 형태로 가능
    val out:() -> Unit = {println("Hihi")}

    out() // 함수처럼 사용이 가능
    val new = out // 다른 변수에 할당도 가능
    new()
}
~~~

#### 람다식과 고차 함수 호출하기 
* 자바나 코틀린은 함수를 호출할떄 인자의 값만 복사하는 '값의 의한 호출'(Call by Value) 가 일반적

* C/C++은 포인터사용. '참조에 의한 호출(Call by Reference)'

* 값에 의한 호출로 람다식 사용하기
~~~kotlin

fun main() {
    val result = callByValue(lambda())
    println(result)
}

fun callByValue(b: Boolean):Boolean {
    println("callByValue function")
    return b
}

val lambda: () -> Boolean = { //람다 표현식이 2줄
    println("lambda function")
    true // 마지막 표현식 문장의 결과가 반환
}

//lambda function
//callByValue function
//true
~~~


* 이름에 의한 함다식 호출
~~~kotlin
fun main() {
    val result = callByName(otherLambda)
    println(result)
}

fun callByName(b: () -> Boolean):Boolean {
    println("callByName function")
    return b()
}

val otherLambda: () -> Boolean = {
    println("otherLambda function")
    true
}

//callByName function
//otherLambda function
//true
~~~

* 다른 함수의 참조에 의한 일반 함수 호출급


지금까지 람다식을 매개변수로 선언해서 사용 // 일반 함수 또는 다른 함수의 인자에서 호출하는 고차함수에 대해서 생각

~~~kotlin
fun main() {
    //sum()함수는 람다식이 아니라 sum 형태로 호출 불가능
    //하지만 매개변수 선언부를 보면 인자의 수와 자료형의 수가 동일하다.
//    val res1 = funcParam(3,2, sum)
    //인자와 반환값이 있는 함수
    val res1 = funcParam(3,2, ::sum)
    println(res1) // 5

    //인자가 없느 함수
    hello(::text) // hi! hello world

    //일반 변수에 값처럼 할당
    val likeLambda = ::sum
    println(likeLambda(6,6)) // 12

}

private fun sum(a: Int, b:Int) = a + b

fun text(a:String, b:String) = "Hi! $a $b"

fun funcParam(a: Int, b:Int, c:(Int, Int) -> Int):Int {
    return c(a,b)
}

fun hello(body: (String, String) -> String):Unit {
    println(body("Hello", "World"))
}
~~~

* 표기법 정리
~~~kotlin
hello(::text) // 함수 참조 기호
hello({a, b -> text(a,b)}) // 람다식 표현(동일한 결과)
hello{a, b -> text(a,b)} // 소괄호 생략 (동일한 결과)
~~~

#### 람다식의 매개변수

1. 람다식에 매개변수가 없는 경우
   
2. 람다식의 매개변수가 1개인 경우 - $it 사용 가능
   
3. 람다식의 매개변수가 2개 이상인 경우 

~~~kotlin
fun main() {
    //매개변수 없는 람다식
    noParam { "Hello World" }                   // Hello World
    noParam ({"Hello World2"})                  // Hello World2

    oneParam ({a-> "Hello World2 $a"})          // OneParam
    oneParam { a-> "Hello World! $a" }          // 소괄호 생략 가능 / OneParam
    oneParam { "Hello World $it" }              // it 으로 대체 가능 (매개 변수가 한개 일 경우) / OneParam

    moreParam({a,b -> "HelloWordl! $a $b"})     //HelloWordl! test Test2
}

//매개변수가 없는 람다식이 noParam 함수의 매개변수 out 으로 지정됨
fun noParam(out: () -> String) = println(out())


// 매개변수가 1개 있는 람다식이 oneParam 함수의 매개변수 out 으로 지정됨
fun oneParam(out:(String) -> String) {
    println("OneParam")
}

//매개변수가 2개
fun moreParam(out: (String, String) -> String) {
    println(out("test", "Test2"))
}

~~~

4. 일반 함수에 람다식 매개변수 2개이상 사용 
~~~kotlin
fun main() {
    twoLambda({a,b -> "First $a $b"}, {"Second $it"})
    twoLambda({a,b -> "First $a $b"}) {"Second $it"}    //위와 동일 표현
}

fun twoLambda(first: (String, String) -> String, second: (String) -> String) {
    println(first("OneParam", "TwoParam"))
    println(second("OneParam"))
}

//First OneParam TwoParam
//Second OneParam
//First OneParam TwoParam
//Second OneParam
~~~

### 3-4. 고차 함수와 람다식의 사례 알아보기

#### 동기화를 위한 코드 구현 구경하기

책 참고 (동기화 / 네트워크 호출)

### 3-5. 코틀린의 다양한 함수 알아보기

1. 익명 함수 

-> 일반 함수이면서 이름이 없는 함수 
~~~kotlin
fun(x: Int, y: Int):Int = x + y

val add:(Int, Int) -> Int = fun(x,y) = x + y  // 변수 선언에 그대로 사용 가능

//선언 자료형을 람다식 형태로 써주면 변수 add는 람다식 함수처럼 add()로 사용 가능
val add = fun(x: Int, y:Int) = x + y
//람다식 표현법
val add = {x : Int, y:Int -> x + y}
~~~

2. 인라인 함수

-> 함수가 호출되는 곳에 함수 본문의 내용을 모두 복사해 넣어 함수의 분기 없이 처리됨.

* 런타임 오버헤드(?)를 줄일 수 있다
* 너무 남용하면 bytecode 양이 많아져, 최적화에 어려움
  -> noinline을 사용하여 줄인다.

~~~kotlin
fun main() {
    //코드상에서 shortFunc를 두번 호출하는것 처럼 보이나, 실제로 한번만 호출
    shortFunc(3) { println("First call: $it")}
    shortFunc(5) { println("Second call: $it")}
}


inline fun shortFunc(a: Int, out:(Int) -> Unit) {
    println("Before calling out()")
    out(a)
    println("After calling out()")
}
~~~

* 인라인 함수와 비지역 반환

1. 익명의 함수를 종료하기 위해 return 사용 가능 -> 인라인 함수에서도 동일하다

~~~kotlin
fun main() {
    shortFunc_third(3) {
        println("First call: $it")
        return //1
    }
}

//crossinline - 비지역 반환을 금지해야하는 람다식에 사용한다.
//inline fun shortFunc_third(a:Int, crossinline out:(Int) -> Unit) {
inline fun shortFunc_third(a:Int, out:(Int) -> Unit) {
    println("Before calling out()")
//    nestedFunc { out(a) }
    out(a)
    println("After calling out()") //2
}

fun nestedFunc(body: () -> Unit) {
    body()
}

//out(a) 는 inline되어 대체됨. -> 2는 실행이 안됨. 이러한 반환을 비지역 반환이라고 한다.
~~~

* 확장 함수
  
  ![확장함수.png](src/com/kotlin/image/extension_func.PNG)
  
* String 클래스에 나만의 확장 함수 추가하기 
  
  -> 길이가 더 긴 문자열을 반환하는 확장 함수 getLongString()을 추가
  
  -> 기존 클래스의 선언부 수정하지 않고 외부에서 손쉽게 확장 가능.

   -> 동일한 이름이 있는 경우는 멤버 메서드가 우선적으로 호출된다.
  
~~~kotlin
fun main() {
    val source = "Hello World!"
    val target = "Kotlin"
    println(source.getLongString(target)) //Hello World
}

//String class에 getLongString 추가
fun String.getLongString(target:String):String =
    if (this.length > target.length) this else target
~~~

* 중위 함수

중위 표현법이란 클래스의 멤버를 호출할떄 사용하는 점(.)을 생략하고 함수 이름 뒤에 소괄호를 붙이지 않아 직관적인 이름을 사용할 수 있는 표현법

~~~kotlin

fun main() {
    //일반 표현법
    //val multi = 3.multiply(10)

    //중위 표현법

    val multi = 3 multiply 10
    println("multi: $multi") //multi: 30
}

infix fun Int.multiply(x:Int): Int {
    return this * x
}

~~~

* 꼬리 재귀 함수

-> 코틀린에서는 꼬리 재귀 함수(Tail Recursive Function) 을 통해 스택 오버플로 현상을 해결 가능

-> tailrec 사용

* factorial 재귀 함수

~~~kotlin
fun main() {
    val number = 4
    val result : Long

    result = factorial(number)
    println("Factorial: $number -> $result")
}

fun factorial(n: Int):Long {
    return if (n==1) n.toLong() else n * factorial(n-1)
}
~~~
* 일반적인 재귀함수는 너무 많은 분기를 할 경우 stack이 넘치는 위험이 있어서 꼬리 재귀를 사용해야한다.


* 꼬리 재귀로 스택 오버플로 방지하기
~~~kotlin
fun main() {
    val number = 5
    println("Factorial: $number -> ${factorial_two(number)}") // 5-> 120
}

tailrec fun factorial_two(n: Int, run:Int = 1): Long{
    return if (n==1) run.toLong() else factorial_two(n-1, run*n)
}
~~~

* 피보나치

~~~kotlin
fun main() {
    val n = 100
    val first = BigInteger("0")
    val second = BigInteger("1")

    println(fibonacci(n, first, second)) // 354224848179261915075
}

tailrec fun fibonacci(n: Int, a:BigInteger, b:BigInteger):BigInteger {
    return if (n == 0) a else fibonacci(n-1, b, a+b)
}
~~~

### 3-6. 함수와 변수의 범위
책 참고.. 

전역 변수 -> global 사용 


# 4. 프로그램의 흐름 제어

<hr>

### 4-1. 조건문

* if - else 기본적인내용..

* 숫자 범위 표현에서 in 사용 하는 방법
~~~kotlin
fun main() {
    print("Enter the score: ")
    val score = readLine()!!.toDouble() //콘솔로부터 입력받는다 / !! not-null 사용
    var grade: Char = 'F'

//    if (score >= 90.0) {
//        grade = 'A'
//    } else if (score >= 80.0 && score <= 89.9) {
//        grade = 'B'
//    } else if (score >= 70.0 && score <= 79.9) {
//        grade = 'C'
//    }

    //변수 이름 in 시작값..마지막값으로 범위 표현 가능
    if (score >= 90.0) {
        grade = 'A'
    } else if (score in 80.0..89.9) {
        grade = 'B'
    } else if (score in 70.0..79.9) {
        grade = 'C'
    }

    println("Score : $score, Grade: $grade")
}
~~~

* when문으로 다양한 조건 처리하기
  -> 자바의 switch 랑 비슷하다.
  
~~~
when (인자) {
    인자에 일치하는 값 혹은 표현식 -> 수행할 문장
    인자에 일치하는 범위 -> 수행할 문장
    ...
    else -> 수행할 문장
}
~~~

* when 문 예시

~~~kotlin
fun main() {
    print("Enter the Score:")

    val score = readLine()!!.toDouble()
    var grade: Char = 'F'

    when(score) {
        in 90.0..100.0 -> grade = 'A'
        in 80.0..89.9 -> grade = 'B'
        in 70.0..79.9 -> grade = 'C'
        !in 70.0..100.0 -> grade = 'F'
    }

    println("Score: $score, Grade: $grade")
}
~~~

*인자가 없는 when 

~~~kotlin
fun main() {
    print("Enter the Score:")

    val score = readLine()!!.toDouble()
    var grade: Char = 'F'

    //인자가 없음
    when {
        score >= 90.0 -> grade = 'A'   //인자 있는 when 문과 다르게 조건식을 구성 가능
        score in 80.0..89.9 -> grade = 'B'
        score in 70.0..79.9 -> grade = 'C'
        score < 70.0 -> grade = 'F'
    }

    println("Score: $score, Grade: $grade")
}
~~~

* 다양한 자료형의 인자 받기
  * Any를 사용하여 다양한 자료형 인자를 받을 수 있다.
    
~~~kotlin
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
~~~

<hr>

### 4-2. 반복문

* for 문
   * 자바는 for문에서 ;(세미콜론) 으로 초기화,조건,증감식을 구별함
   * 코틀린은 앞에서 배운 in 연산자와 함께 사용.
    
~~~kotlin
fun main() {
    var sum = 0

    for (x in 1..10) sum+=x
    print("sum : $sum") //sum: 55
}
~~~

* 하행,상행 및 다양한 반복 방법
   * 하행은 in 대신 downTo를 써야한다(조금 불편한데..?)
  ~~~kotlin
  for (i in 5 downTo 1) print(i)
  ~~~
   
   * 홀수의 계수만 사용하기 위해서는 step 사용
  ~~~kotlin
  for (i in 1..5 step 2) print(i)
  ~~~
  
* for문을 활용한 삼각형 출력하기


~~~kotlin
fun main() {
    print("Enter the lines:")
    val n = readLine()!!.toInt()

    for (line in 1..n){
        for (space in 1..(n-line)) print(" ") //공백 출력
        for (star in 1..(2* line -1)) print("*") //별표 출력
        println() //개행
    }
}
~~~

* 1 ~ 100의 홀수 합 구하기

~~~ kotlin
fun main() {
    var total: Int = 0

    for (num in 1..100 step 2) total += num
    println("Odd total: $total")  //2500

    for (num in 0..99 step 2) total+= num
    println("Even totla: $total") //4950


}
~~~

* while문 , do-while 문
   * 책 참고
~~~
while(조건식 {
    본문
    ....
)
~~~

~~~
do {
    본문
} while (조건식)
~~~

<hr>

### 4-3. 흐름의 중단과 반환

* return : 결과값 반환 / 지정된 라벨로 이동
* break : for문이나 while문의 조건식 상관없이 반복문 종료
* continue : for문이나 while문의 본문을 모두 수행하지 않고 조건식으로 넘어간다.

* try(...) catch(...) : 예외 발싱시 catch 블록의 본문 실행
* try(...) catch(...) finally(...) : 예외가 발생해도 finally 블록은 무조건 실행

* return 으로 Unit 반환하기

~~~kotlin
//1. Unit 명시적 표현
fun hello(name: String) :Unit {
    println(name)
    return Unit
}

//2. Unit 이름을 생략
fun hello(name: String) :Unit {
    println(name)
    return
}

//3. return 자체를 생략
fun hello(name: String) :Unit {
    println(name)
}
~~~

* 람다식에서 return 사용하기
    * 람다식에서는 break,continue는 사용못한다.

~~~kotlin
fun main() {
    retFunc()
}

inline fun inlineLambda(a: Int, b:Int, out: (Int, Int) -> Unit) {
    out(a, b)
}

fun retFunc() {
    println("start of retFunc")               //1
    inlineLambda(10 ,3) { a,b ->        //2
        val result = a + b
        if(result > 10) return              //3 10보다 크면 이 함수를 빠져 나간다. -> 함수자체를 빠져나감
        println("result : $result")         //4 10보다 크면 이 문장에 도달 못함
    }
    println("end of retFunc")               //5

    //출력 : 10보다 큰 경우
~~~

* 람다식에서 라벨과 함께 return 사용하기
    * 라벨을 정의해 return 사용
    
~~~ 
람다식 함수 이름 라벨 이름@ {
    ...
    return@라벨 이름
}
~~~

~~~kotlin
fun main() {
    retFunc()
}

private inline fun inlineLambda(a: Int, b:Int, out: (Int, Int) -> Unit) {
    out(a, b)
}

private fun retFunc() {
    println("start of retFunc")
    inlineLambda(10 ,3) lit@{ a,b ->        //1 람다식 블록의 시작 부분에 라벨을 지정
        val result = a + b
        if(result > 10) return@lit              //2 라벨을 사용한 블록의 끝부분 반환
        println("result : $result")
    }
    println("end of retFunc")                   //라벨을 사용하여 이 부분이 실행된다.

    //출력 : start of retFunc
    //      end of retFunc
}
~~~

* 암묵적 라벨
    * 람다식 표현식 블록에 직접 라벨을 쓰는 것이 아닌 람다식의 명칭을 그대로 라벨처럼 사용
    
~~~kotlin
fun main() {
    retFunc()
}

private inline fun inlineLambda(a: Int, b:Int, out: (Int, Int) -> Unit) {
    out(a, b)
}

private fun retFunc() {
    println("start of retFunc")
    inlineLambda(10 ,3){ a,b ->        //1 람다식 블록의 시작 부분에 라벨을 지정
        val result = a + b
        if(result > 10) return@inlineLambda              //2 라벨을 사용한 블록의 끝부분 반환
        println("result : $result")
    }
    println("end of retFunc")                   //라벨을 사용하여 이 부분이 실행된다.

    //출력 : start of retFunc
    //      end of retFunc
}
~~~

* 익명 함수를 사용한 반환
    * 람다식 대신에 익명 함수를 사용 가능
    * 이떄는 라벨을 사용하지 않고도 가까운 익명 함수 자체가 반환됨 -> 위와 동일한 결과
    
~~~kotlin
private fun retFunc() {
    println("start of retFunc")
    inlineLambda(10 ,3, fun (a,b) {       
        val result = a + b
        if(result > 10) return         
        println("result : $result")
    })
    println("end of retFunc")                   

    //출력 : start of retFunc
    //      end of retFunc
}
~~~

~~~kotlin
// 람다식 방법
val getMessage = lambda@ { num : int ->
    if(num !in 1..100) {
        return@lambda "Error"  //라벨을 통한 반환
    }
    "Success"   //마지막 식이 반환
}

// 익명 함수 방법
val getMessage = fun(num : int):String { 
    if(num !in 1..100) {
        return "Error"  
    }
    return "Success"   
}
~~~

* 람다식과 익명 함수를 함수에 할당할 때 주의할 점

~~~
fun greet() = {println("Hello")}

greet() -> 아무것도 출력이 안됨.  {println("Hello")} 자체가 greet()함수에 할당된 것 뿐

greet()함수가 가지고 있는 함수를 쓰기 위해서는

greet()() 를 사용해야한다.

또는

fun greet() = fun(){println("Hello")}
~~~

* break 문과 continue문
  * 책참고 - 라벨 사용 가능
  
* 예외 처리

~~~kotlin
fun main() {
    val a = 6
    val b = 0
    val c : Int

    try {
        c = a / b       //0으로 나눔
    } catch (e: Exception) {
        println("Exception is handled")
    } finally {
        println("finally 블록은 반드시 실행된다.")
    }
}
~~~

* 예외 발생시키기 
  * throw 사용
  
~~~kotlin
fun main() {
    var amount = 600
    
    try {
        amount -= 100
        checkAmount(amount)
    } catch (e: Exception) {
        println(e.message)
    }
    println("amount: $amount")
}

fun checkAmount(amount: Int) {
    if (amount < 1000)
        throw Exception("잔고가 $amount 으로 1000이하입니다")
}
~~~

* 사용자 정의 예외

~~~ kotlin
class InvalidNameException(message: String) : Exception(message)    //사용자가 정의한 예외 클라스

fun main() {
    var name = "Kildong123" //숫자가 포함된 이름

    try {
        validateName(name)
    } catch (e : InvalidNameException) {    // 숫자가 포함된 예외처리
        println(e.message)
    } catch (e: Exception) {    //기타 예외처리
        println(e.message)
    }


}

fun validateName(name: String) {
    if (name.matches(Regex(".*\\d+.*"))) {
        throw InvalidNameException("Your name : $name : contains numerals")
    }
}
~~~

# 객체 지향 프로그래밍

# 5. 클래스와 객체

<hr>

### 5-1. 클래스와 객체의 정의
* 추상화 : 특정 클래스를 만들 때 기본 형식을 규정하는 방법
* 인스턴스 : 클래스로부터 생성한 객체  
* 상속 : 부모 클래스의 내용을 자식 클래스가 그대로 물려받음
* 다형성 : 하나의 이름으로 다양한 처리를 제공
* 캡슐화 : 내용을 숨기고 필요한 부분만 사용
* 메시지 전송 : 객체 간에 주고받는 메시지
* 연관 : 클래스 간의 관계

<br>

* 객체 지향 프로그래밍과 용어
  * 생성자와 초기화 블록 : 객체가 생성될 때 자동 실행되는 메서드 또는 코드 블록
  * 프로퍼티 : 변수의 이름과 변수의 접근 함수가 포함된 형태
  * 메서드: 일반적인 함수의 형태
  * 중첩 클래스와 이너 클래스 : 클래스 내부에 구성되는 클래스
  * 객체 선언 : 클래스 없이 접근할 수 있는 객체
  
* 클래스 다이어그램
  * 책 참고 / 구글 참고
  
* 클래스와 추상화
  * 클래스 : 특정 기본 개념을 정의하는 것
  * 추상화 : 우리가 목표로 하는 대상에 대해 필요한 만큼 속성과 동작을 정의하는 것
  
* 클래스 선언하기
~~~ 
class Bird{
  //프로퍼티
  //메서드
}  //빈 내용
class Bird    // 중괄호 생략
~~~

~~~kotlin
class Bird {
    var name : String = "myBird"
    var wing : Int = 2
    var color : String = "blue"

    fun fly() = println("Fly~~")
    fun sing(vol:Int) = println("Sing vol: $vol")
}

fun main() {
    val coco = Bird()
    coco.color = "red"

    println("coco.color : ${coco.color}")
    coco.fly()
    coco.sing(100)
}

//coco.color : red
//Fly~~
//Sing vol: 100
~~~

* 객체와 인스턴스 정리하기

'클래스로 부터 객체가 생성됩니다.'

'**은 xx클래스의 인스턴스입니다.'

<hr>

### 5-2. 생성자

* 생성자(Constructor) - 클래스를 통해 객체가 만들어질 때 기본적으로 호출되는 함수
  * 생성자는 주 생성자(primary construcor) 와 부 생성자 (Secondary Constructor) 로 나누어짐

~~~
class 클래스 이름 constructor(필요한 매개변수..) {
  ...
  constructor(필요한 매개변수..) { //부 생성자 위치
    //프로퍼티의 초기화
  }
  [constructor(필요한 매개변수..) {...}]
  ...
}
~~~

* 부 생성자
  * 하기 로직과 책 참조..
  
~~~kotlin
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
~~~





