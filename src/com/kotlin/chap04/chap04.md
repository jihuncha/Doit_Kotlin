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