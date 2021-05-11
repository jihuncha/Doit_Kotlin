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

* 주 생성자
    * 주 생성자는 클래스 이름과 함께 생성자 정의를 이용할 수 있는 기법

* 프로피터를 포함한 주 생성자
~~~kotlin
class Bird(var name: String, var wing: Int, var beak: String, var color:String) {
    // 프로퍼티는 매개변수 안에 var를 이용해 프로퍼티로서 선언되어 본문에서 생략함

    fun fly() = println("Fly wing: $wing")
    fun sing(vol:Int) = println("Sing vol:$vol")
}

fun main() {
    val coco = Bird("mybird", 2, "short", "blue")

    coco.color = "yellow"
    println("coco.color : ${coco.color}")
    coco.fly()
    coco.sing(3)
}
~~~

* 초기화 블록을 가진 주 생성자
    * init 사용 - 객체 생성 시점에서 코드 수행 문장을 실행 할 수 있다.

~~~kotlin
class Bird (var name: String, var wing: Int, var beak: String, var color:String){
    // 초기화 블록
    init {
        println("--------초기화 블록 시작------------")
        println("이름은 $name, 부리는 $beak")
        this.sing(3)
        println("--------초기화 블록 끝------------")
    }

    fun fly() = println("Fly wing: $wing")
    fun sing(vol:Int) = println("Sing vol:$vol")
    
}

fun main() {
    val coco = Bird("mybird", 2, "short", "blue")   //생성과 함께 초기화블록이 실행된다.

    coco.color = "yellow"
    println("coco.color : ${coco.color}")
    coco.fly()
}
~~~

* 프로퍼티의 기본값 지정

~~~kotlin
//매개변수에 선언을 하여 기본값 지정 가능
class Bird (var name: String = "NoName", var wing: Int,
            var beak: String, var color:String) {
    
}
~~~

<hr>

### 5-3. 상속과 다형성

* 상속
    * 자식 클래스를 만들떄 상위 클래스의 속성과 기능을 계승

* 다형성
    * 같은 이름을 사용하지만 구현 내용이 다르거나 매개변수가 달라서 하나의 이름으로 다양한 기능을 수행하는 개념

* 상속과 클래스의 계층
  ![기본 클래스와 파생된 하위 클래스.png](src/com/kotlin/image/Inheritance.PNG)


* 하위 클래스 선언하기
    * 변수 선언과 클래스 상속이 똑같은 콜론(:) 기호를 사용한다.
~~~
open class 기반 클래스 이름 { //묵시적으로 Any로 부터 상속됨, open으로 파생 가능
  ...
}
class 파생 클래스 이름 : 기반 클래스 이름() { // 기반 클래스로부터 상속됨, 최종 클래스로 파생 불가
  ...
}
~~~

~~~kotlin
//상속 가능한 클래스를 선언하기 위해 open 사용
open class Bird(var name:String, var wing: Int, var beak:String, var color:String) {
    //메서드
    fun fly( ) = println("Fly wing: $wing")
    fun sing(vol:Int) = println("Sing vol: $vol")
}

//주 생성자를 사용하는 상속
class Lark(name:String, wing: Int, beak:String, color:String) : Bird(name,wing,beak,color) {
    fun singHitone() = println("Happy Song!!") // 새로 추가한 메서드
}

//부 생성자를 사용하는 상속
class Parrot : Bird {
    val language: String
    constructor(name: String, wing: Int, beak:String, color:String, language:String) : super(name,wing,beak,color) {
        this.language = language // new 프로퍼티
    }

    fun speak() = println("Speak! $language")
}

fun main() {
    val coco = Bird("mybird", 2, "short", "blue")
    val lark = Lark("mylark", 2, "long", "brwon")
    val parrot = Parrot("myparrot", 2, "short", "multiple","korean")

    println("Coco: ${coco.name}, ${coco.wing}, ${coco.beak}, ${coco.color} ")
    println("Lark: ${lark.name}, ${lark.wing}, ${lark.beak}, ${lark.color} ")
    println("Parrot: ${parrot.name}, ${parrot.wing}, ${parrot.beak}, ${parrot.color}, ${parrot.language} ")

    lark.singHitone()   //추가한 메서드
    parrot.speak()
    lark.fly()
}

/*
* Coco: mybird, 2, short, blue 
Lark: mylark, 2, long, brwon 
Parrot: myparrot, 2, short, multiple, korean 
Happy Song!!
Speak! korean
Fly wing: 2
* */
~~~

* 다형성
    * 이름만 동일하고 매개변수를 다르게 / 실행 결과를 다르게
    * 동작은 같지만 인자의 형식이 다른것 : 오버로딩(Overloading)
    * 상위와 하위 클래스에서 메서드나 프로퍼티 이름은 같지만 기존의 동작을 다른 동작으로 재정의 : 오버라이딩(Overriding)


* 오버로딩

~~~kotlin
fun main() {
    val calc = Calc()
    println(calc.add(3,2))
    println(calc.add(3.2,2.1))
    println(calc.add(3,3, 2))
    println(calc.add("Hello","World"))
}

class Calc{
    fun add(x: Int, y:Int) : Int = x+y
    fun add(x: Double, y:Double) :Double = x+y
    fun add(x: Int, y:Int, z:Int) :Int = x+y+z
    fun add(x: String, y:String) :String = x+y
}

/*
* 5
5.300000000000001
8
HelloWorld
* */
~~~

* 오버라이딩
  * 코틀린에서는 기반 클래스에서 open키워드, 파생 클래스에서 override 키워드를 사용
  
