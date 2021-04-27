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
//인자로 함수 사
fun main() {
    val res1 = sum(3,2)
    val res2 = mul(sum(3,3), 3)

    println("res1: $res1, rest2: $res2")
}

fun sum(a:Int, b:Int) = a+b
fun mul(a:Int, b:Int) = a*b
~~~

~~~kotlin
//반환값으로 함수 사
fun main() {
    println("funcfunc : ${funcFunc()}")
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
    val multi = {x:Int, y:Int -> x * y}
    result = multi(10, 20)
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
    //자료형 추론이 가능하므로 val out = {prinln("Hello World")} 같은 형태로 가
    val out:() -> Unit = {println("Hihi")}

    out()
    val new = out
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
ㅖ
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