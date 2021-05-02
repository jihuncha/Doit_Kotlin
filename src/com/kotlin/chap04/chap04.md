# 4. 프로그램의 흐름 제어

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
