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
  
  ![기본 클래스와 파생된 하위 클래스.png](D:\test_space_second\kt_project\src\com\kotlin\image\Inheritance.PNG)


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
~~~kotlin
// 상속 가능한 클래스를 위해 open 사용
open class Bird(var name:String, var wing: Int, var beak:String, var color:String) {
    // 메서드
    fun fly( ) = println("Fly wing: $wing")
    open fun sing(vol: Int) = println("Sing vol: $vol")
}

class Parrot(name: String, wing: Int = 2, beak: String, color: String, var language:String = "natural") : Bird(name,wing, beak, color) {
    fun speak() = println("Speak! $language")   // parrot에 추가된 메서드
    override fun sing(vol: Int) {   //오버 라이딩된 메서드
        println("I'm a parrot! The volume level is $vol")
        speak() //달라진 내용
    }
}

fun main() {
    val parrot = Parrot(name="myparrot", beak = "short", color = "multiple")
    parrot.language = "English"
    
    println("Parrot : ${parrot.name},  ${parrot.wing}, ${parrot.beak}, ${parrot.color}, ${parrot.language}")
    parrot.sing(5)  //달라진 메서드
    
}

/*
* Parrot : myparrot,  2, short, multiple, English
I'm a parrot! The volume level is 5
Speak! English
* */
~~~

<hr>

### 5-4. super와 this의 참조


|super|this|
|------|---|
|super.프로퍼티 이름 // 상위 클래스의 프로퍼티 참고|this.프로퍼티 이름 //현재 클래스의 프로퍼티 참조|
|super.메서드 이름() // 상위 클래스의 메서드 참조|this.메서드 이름() // 현재 클래스의 메서드 참조|
|super() // 상위 클래스의 생성자 참조|this() // 현재 클래스의 생성자 참조|

* super로 상위 객체 참고하기

~~~kotlin
open class Person {
    constructor(firstName:String) {
        println("[Person] firstName: $firstName")
    }

    constructor(firstName:String, age:Int) {    //3. 먼저 출력된다.
        println("[Person] firstName: $firstName, $age")
    }
}

class Developer: Person {
    constructor(firstName: String): this(firstName, 10) {   //1. 처음에 여기 this 호출
        println("[Developer] $firstName")
    }

    constructor(firstName: String, age:Int): super(firstName, age) {    //2.super로 이동
        println("[Developer] $firstName")
    }
}

fun main() {
    val sean = Developer("Sean")
}

/*
*
[Person] firstName: Sean, 10
[Developer] Sean
[Developer] Sean
*
* */
~~~

* 주 생성자와 부 생성자 함께 사용하기

~~~kotlin
class Person(firstName: String, out:Unit = println("[Primary Constructor] Paramerter")) {   //주생성자
    val fName = println("[Property] Person fName: $firstName")  //프로퍼티 할당

    init {
        println("[init] Person init block") //초기화 블록
    }

    //부 생성자
    constructor(firstName: String, age:Int, out: Unit = println("[Secondary Constructor] Paramerter")): this(firstName) {
        println("[Secondary Constructor] Body: $firstName, $age")   //부 생성자 본문
    }
}

fun main() {
    val p1 = Person("Kildong", 30)
    println()
    val p2 = Person("Dooly")
}

/*
* [Secondary Constructor] Paramerter
[Primary Constructor] Paramerter
[Property] Person fName: Kildong
[init] Person init block
[Secondary Constructor] Body: Kildong, 30

[Primary Constructor] Paramerter
[Property] Person fName: Dooly
[init] Person init block
* 
* */
~~~

* 바깥 클래스 호출하기
  * 특정 클래스 안에 선언된 클래스를 이너 클래스(inner class) 라고 한다.
  * 이너 클래스에서 바깥 클래스의 상위 클래스를 호출할려면 super키워드와 함께 @ 기호 옆에 바깥 클래스 이름을 작성

~~~kotlin
open class Base {
    open val x: Int = 1
    open fun f( ) = println("Base Class f()")
}

class Child: Base( ) {
    override val x: Int = super.x + 1
    override fun f ( ) = println("Child Class f( )")

    inner class Inside {
        fun f( ) = println("Inside Class f( )")
        fun test( ) {
            f() //현재 이너 클래스의 f() 접근
            Child().f() //바로 바깥 클래스의 f() 접근
            super@Child.f() //Child 상위 클래스인 Base클래스의 f() 접근
            println("[Inside] super@Child.x: ${super@Child.x}") //Base의 x접근
        }
    }
}

fun main() {
    val c1 = Child()
    c1.Inside().test()
}
~~~

* 인터페이스에서 참조하기
  * 인터페이스(Interface) : 구현의 약속. 인터페이스를 참조하는 클래스는 인터페이스가 가지고 있는 내용을 구현해야하는 가이드를 제시한다.
  * 코틀린은 자바처럼 다중 상속이 되지 않는다. // 인터페이스로는 필요한 만큼 다수의 인터페이스를 지정해 구현할 수 있다.
  * 이때 각 인터페이스의 프로퍼티/메서드이름이 동일할 수 있다.
  * 그런 경우 앵글 브래킷(<>) 을 사용해 접근하려는 클래스나 인터페이스 이름을 정해준다.

~~~kotlin
open class A {
    open fun f() = println("A Class f()")
    fun a( ) = println("A Class a()")
}

interface B {
    fun f( ) = println("B interface f( )")  //인터페이스는 기본적으로 open임
    fun b( ) = println("B interface b( )")
}

class C: A( ), B {  //쉼표를 사용해 클래스와 인터페이스를 지정
    // 컴파일되려면 f( )가 오버라이딩 되어야 한다.
    override fun f( ) = println("C Class f( )")

    fun test( ) {
        f() //현재 클래스의
        b() //인터페이스의
        super<A>.f()    //A 클래스의
        super<B>.f()    //B 클래스의
    }
}

fun main() {
    val c = C( )
    c.test()
}

/*
C Class f( )
B interface b( )
A Class f()
B interface f( )*/

~~~

<hr>

### 5-5. 정보 은닉 캡슐화

* 캡슐화(Encapsulation) - 클래스를 작성할 때 숨겨야 하는 속성이나 기능

* 가시성 지시자
  * 각 클래스나 메서드, 프로퍼티의 접근 범위를 가시성(Visibility) 라고 한다.

~~~
* private : 외부에서 접근 불가능
* pulbic : 어디서든 접근 가능
* protected : 외부에서 접근할 수 없으나 상속 요소에서는 가능
* internal : 같은 정의의 모듈 내부에서는 접근이 가능
~~~


![가시성 접근자.png](D:\test_space_second\kt_project\src\com\kotlin\image\visibility_modifier.PNG)

* private

~~~kotlin
private class PrivateClass {
    private var i = 1
    private fun privateFunc() {
        i += 1  //접근 허용
    }
    fun access() {
        privateFunc() // 접근 허용
    }
}

class otherClass {
//    val opc = PrivateClass()    //접근 불가
    fun test() {
        val pc = PrivateClass() //접근 가능
    }
}

fun main() {
    val pc = PrivateClass() //생성 가능
//    pc.i        // 접근 불가능
//    pc.privateFunc()    //접근 불가

}

fun TopFunction() {
    val tpc = PrivateClass()    //객체 생성 가능
}
~~~

* protected

~~~kotlin
open class Base {   //최상위 클래스에서는 protected를 사용할 수 없음
    protected var i = 1
    protected fun protectedFunc( ) {
        i += 1  //접근 허용
    }
    fun access() {
        protectedFunc() //접근 허용
    }
    protected class Nested //내부 클래스에는 지시자 허용
}

class Derived : Base() {
    fun test(base: Base):Int {
        protectedFunc()     //Base 클래스의 메서드 접근 가능
        return i            //Base 클래스의 프로퍼티 접근 가능
    }
}

fun main() {
    val base = Base()   //생성 가능
//    base.i  // 접근 불가
//    base.protectedFunc() // 접근 불가
    base.access() // 접근 가능
}
~~~

* internal
  * 자바와 다르게 새로 정의된 이름
  * Project단위의 Module을 의미한다.

~~~kotlin
internal class InternalClass {
    internal var i = 1
    internal fun icFunc() {
        i += 1  //접근 허용
    }
    fun access() {
        icFunc()    //접근 허용
    }
}

class Other {
    internal val ic = InternalClass()   //프로퍼티를 지정할떄 internal로 맞춰야함
    fun test() {
        ic.i        //접근가능
        ic.icFunc() //접근가능
    }
}

fun main() {
    val mic = InternalClass()
    mic.i
    mic.icFunc()            //생성 및 접근 모두 가능
}
~~~

* 다른 파일에서도 같은 모듈이면 접근 가능

~~~kotlin
fun main() {
    val otheric = InternalClass()

    println(otheric.i)
    otheric.icFunc()
}
~~~

* 가시성 지시자와 클래스의 관계
  ![가시성 접근자와 클래스의 관계.png](..\image\simple_example.PNG)


* 자동차와 도둑의 예제
  * 자동차 - Car 클래스
  * Tico - 자동차 클래스 상속
  * 도둑 - 외부 클래스 Burglar
  * 소스 : ([예제](section5\burglar\CarVisibilityPublic.kt))

<hr>

### 5-6. 클래스와 클래스의 관계

* 클래스 혹은 객체 간의 관계
  * 약한 참조 - 소유의 개념 없이 어떤 객체에서 또 다른 객체를 '이용한다'
    1. 연관(Association)
    2. 의존(Dependency)
  * 집합 (Aggregation)  - 서로 따로 떨어져도 문제가 없음
  * 구성 (Composition)  - 두 개체가 밀접하게 관련되어 독립적으로 존재가 어려움

* 클래스 간의 관계를 판별하는 방법
  * 연관 관계 (Association)
    - 서로 분리된 클래스가 연결을 가지는 것 (단방향/양방향)
    - 두 요소가 서로 다른 생명 주기를 가지고 있다.
    - 소스 : ([연관 관계](D:\test_space_second\kt_project\src/com/kotlin/chap05/section6/association/AssociationTest.kt))

  * 의존 관계 (Dependency)
    - 소스 : ([의존 관계](D:\test_space_second\kt_project\src/com/kotlin/chap05/section6/dependency/DependencyTest.kt))

  * 집합 관계 (Aggregation)
    - 소스 : ([집합 관계](D:\test_space_second\kt_project\src/com/kotlin/chap05/section6/AggregationTest.kt))

  * 구성 관계 (Composition)
    - 소스 : ([구성 관계](D:\test_space_second\kt_project\src/com/kotlin/chap05/section6/composition/CompositionTest.kt))

* 객체 간의 메시지 전달하기
  - UML의 시퀀스 다이어그램 으로 표현
  
