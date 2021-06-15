# 6. 프로퍼티와 초기화

<hr>

### 6-1. 프로퍼티의 접근
    * 프로퍼티 : 클래스 내에 선언한 변수 
    * 자바와 다른 점 : 변수 선언 부분과 기본적인 접근 메서드를 모두 가지고 있음

<br>

#### 자바에서 필드를 사용할 때의 문제점
* Getter/Setter를 생성해줘야한다.
* 그러나 코틀린에서는 필요없다!!

<br>


#### 코틀린에서 게터와 세터가 작동하는 방식

~~~kotlin
// 1. 주 생성자에 3개의 매개변수 정의 (내부 선언 버전)
//class User(_id: Int, _name: String, _age: Int) {
//    // 프로퍼티
//    val id: Int = _id // 불변(읽기 전용)
//    var name: String = _name // 변경 가능
//    var age: Int = _age // 변경 가능
//}

// 2. 간소화된 버전
class User(val id: Int, var name: String, var age: Int)

fun main( ) {
    val user = User(1, "Sean", 30)
    val name = user.name // 게터에 의한 값 획득
    user.age = 41 // 세터에 의한 값 지정
    println("name: $name, ${user.age}")
}
~~~

<br>

#### 기본 게터와 세터 직접 지정하기
    * value: 세터의 매개변수로 외부로부터 값을 가져옴
    * field: 프로퍼티를 참조하는 변수

~~~kotlin
// 직접 구성한 기본 게터/세터
class User_second(_id: Int, _name: String, _age: Int) {
    // 프로퍼티들
    // value, field라는 축약어 등장!!
    val id: Int = _id
        get() = field

    var name: String = _name
        get() = field
        set(value) {
            field = value
        }

    var age: Int = _age
        get() = field
        set(value) {
            field = value
        }
}

fun main() {
    val user1 = User_second(1, "Kildong", 30)
    // user1.id = 2  // val 프로퍼티는 값 변경 불가
    user1.age = 35 // 세터
    println("user1.age = ${user1.age}") // 게터
}
~~~

#### 보조 필드의 역할
    * field는 프로퍼티를 참조하는 변수 (보조 필드 Backing Field) 라고 함
    * get() = field는 결국 각 프로퍼티의 값을 읽는 특별한 식별자
    * get() = age와 같이 사용하면 프로퍼티의 get()을 사용하는 것과 마찬가지 = 무한 재귀 호출에 빠진다.

#### 커스텀 게터와 세터의 사용
    * 사용자가 직접 게터와 세터를 정의하면서 새로운 내용을 작성하는 것
    * 단순히 값을 반환하거나 설정할 때는 굳이 사용할 필요가 없다.
    * 내부 set 앞에 private 를 선언하여 외부에서 접근을 방지할 수도 있다.

~~~kotlin
// 커스텀 게터/세터의 사용
class User_third(_id: Int, _name: String, _age: Int) {
    val id: Int = _id
    var name: String = _name
        set(value) {
            println("The name was changed")
            field = value.toUpperCase() // 받은 인자를 대문자로 변경해 프로퍼티에 할당
        }

    var age: Int = _age
}

fun main() {
    val user1 = User_third(1, "kildong", 35)
    user1.name = "coco"     //the name was changed 호출됨.
    println("user3.name = ${user1.name}")
}
~~~

#### 보조 프로퍼티의 사용
    * 만일 보조 필드를 사용하지 않는 경우에는 암시적으로 사용할 프로퍼티를 선언해 놓고 게터나 세터에 사용할 수 있다.

~~~kotlin
// 커스텀 게터/세터의 사용
class User_fourth(_id: Int, _name: String, _age: Int) {
    val id: Int = _id
    private var tempName: String? = null
    var name: String = _name
        get() {
            if (tempName == null) tempName = "NONAME"
            return tempName ?: throw AssertionError("Asserted by others")
        }
        set(value) {
            println("The name was changed")
            field = value.toUpperCase() // 받은 인자를 대문자로 변경해 프로퍼티에 할당
        }

    var age: Int = _age
}

fun main() {
    val user1 = User_fourth(1, "kildong", 35)
    user1.name = ""
    println("user3.name = ${user1.name}")
}
~~~

#### 프로퍼티의 오버라이딩
    * 프로퍼티는 기본적으로 오버라이딩 할 수 없는 final 형태로 선언
    * open 키워드를 사용하여 오버라이딩이 가능하도록

~~~kotlin
open class First {
    open val x: Int = 0
        get() {
            println("First x")
            return field
        }
    val y: Int = 0 // open 키워드가 없으면 final 프로퍼티임
}

class Second : First() {
    override val x: Int = 0
        get() { // 부모와 구현이 다름
            println("Second x")
            return field + 3
        }
    // override val y: Int = 0 // 에러! 오버라이딩 불가
}

fun main() {
    val second = Second()
    println(second.x) // 오버라이딩된 두번째 클래스 객체의 x
    println(second.y) // 부모로 부터 상속 받은 값
}
~~~

#### 프로퍼티를 이용한 나이 속이기 예제

~~~kotlin
fun main() {
    val kim = FakeAge()
    kim.age = 15
    println("Kim's real age = 15, pretended age = ${kim.age}")

    val hong = FakeAge()
    hong.age = 35
    println("Hong's real age = 35, pretended age = ${hong.age}")
}

class FakeAge {
    var age: Int = 0
        set(value) { // 나이에 따라 판별하는 세터
            field = when {
                value < 18 -> 18
                value in 18..30 -> value
                else -> value - 3
            }
        }
}

~~~

### 6-2. 지연 초기화와 위임
    * 객체의 정보가 나중에 나타나는 경우 객체 생성과 동시에 초기화하기 어려운 경우를 방지하기 위함
    * lateinit / lazy 키워드 사용

#### lateinit을 사용한 지연 초기화
    * 기본 자료형들은 생성자에서 반드시 초기화해야하지만, 츼존성이 있는 초기화나 유닛 테스를 위한 코드를 작성하면서 설정에 의한 초기화를 할 경우에 사용
    * 프로퍼티를 즉시 사용하지 않는데도 미리 생성해서 초기화하는 경우 (메모리 낭비의 경우)

#### lateinit의 제한
    * var로 선언된 프로퍼티만 가능하다
    * 프로퍼티에 대한 게터와 세터를 사용할 수 없다.

~~~kotlin
// lateinit 사용의 예
class Person {
    lateinit var name: String

    fun test() {
        //::은 프로퍼티 참조를 위해 사용
        //! 은 not 의 의미
        if(!::name.isInitialized) { // 프로퍼티의 초기화 여부 판단
            println("not initialized")
        } else {
            println("initialized")
        }
    }
}

fun main() {
    val kildong = Person()
    kildong.test()
    kildong.name = "Kildong" // 이 시점에서 초기화가 된다. (지연초기화)
    kildong.test()
    println("name = ${kildong.name}")
}
~~~

#### 객체 지연 초기화하기
~~~kotlin
data class Person(var name:String, var age:Int)

lateinit var person1: Person

fun main() {
    person1 = Person("Kildong", 30) //생성자 호출 시점에서 초기화됨
}
~~~

#### lazy를 사용한 지연 초기화
    * lateinit은 val을 허용하지 않는다.
    * val 전용으로 lazy를 사용!
    * 특징
      1. 호출 시점에 by lazy(...) 정의에 의해 블록 부분의 최고하를 진행한다.
      2. 불변의 변수 선언인 val에서만 사용 가능하다(읽기 전용)
      3. val이므로 값을 다시 변경할 수 없다.

#### 프로퍼티 지연 초기화하기
    * lazy는 람다식으로 구성되어 lazy인스턴스 반환값을 가지는 함수

~~~kotlin
class LazyTest {
    init {
        println("init block") // (2)
    }

    val subject by lazy {
        println("lazy initialized") // (6)
        "Kotlin Programming" // (7) lazy 반환값
    }
    fun flow() {
        println("not initialized") // (4)
        println("subject one: $subject") // (5) 최초 초기화 시점!
        println("subject two: $subject") // (8) 이미 초기화된 값 사용
    }
}

fun main() {
    val test = LazyTest() // (1)
    test.flow() // (3)
}

//init block 
//not initialized
//lazy initialized
//subject one: Kotlin Programming
//subject two: Kotlin Programming
~~~

#### 객체 지연 초기화하기

~~~kotlin
class Person(val name: String, val age: Int)

fun main() {
    var isPersonInstantiated: Boolean = false

    val person : Person by lazy {           //by lazy 를 사용한 person 객체의 지연 초기화
        isPersonInstantiated = true
        Person("Kim", 23)           // 이 부분이 lazy 객체로 반환됨.
    }
    val personDelegate = lazy { Person("Hong", 40) }

    println("person Init: $isPersonInstantiated")
    println("personDelegate Init: ${personDelegate.isInitialized()}")

    println("person.name = ${person.name}")  // 이 시점에서 초기화
    println("personDelegate.value.name = ${personDelegate.value.name}")  // 이 시점에서 초기화

    println("person Init: $isPersonInstantiated")
    println("personDelegate Init: ${personDelegate.isInitialized()}")
}
~~~

#### lazy 모드 확인하기
    * lazy()는 매개변수 없는 람다식을 받을 수 있으며 Lazy<T>를 반환
    * lazy()의 실행은 구현부 SynchronizedLazyImp()에 보내 처리
    * mode의 경우 Synchronized, Publication, None 으로 지정 가능
      1. SYNCHRONIZED: lock을 사용해 단일 스레드만이 사용하는 것을 보장
      2. PUBLICATION: 여러 군데에서 호출될 수 있으나, 처음 초기화된 후 반환값을 사용한다.
      3. NONE: lock을 사용하지 않기 때문에 빠르지만 다중 스레드가 접근할 수 있다.(값의 일관성을 보장할 수 없음)

![lazy모드.png](src/com/kotlin/image/lazy_mode.PNG)

#### by를 이용한 위임
    * 위임 (Delegation) - 어떤 특정일을 대신해주는 중간자 역할
    * 특정 클래스를 확장하거나 이용할 수 있도록 by를 사용 가능
    * by를 사용하면 하나의 클래스가 다른 클래스에 위임을 함으로써 `클래스가 가지는 멤버를 참조없이 호출 가능` 
    * <val|var|class> 프로퍼티 혹은 클래스이름 : 자료형 by 위임자 

#### 클래스의 위임
* 위임을 사용하는 이유 : 코틀린의 표준 라이브러리는 open으로 정의되지 않는 클래스를 사용하고 있다. = 모두 final형태
  -> 상속이나 직접 클래스 확장이 어려움


~~~kotlin
interface Car {
  fun go(): String
}

class VanImpl(val power: String): Car {
  override fun go() = "는 짐을 적재하며 $power 마력을 가집니다."
}

class SportImpl(val power: String): Car {
  override fun go() = "는 경주용에 사용되며 $power 마력을 가집니다."
}

class CarModel(val model: String, impl: Car): Car by impl {
  fun carInfo() {
    println("$model ${go()}") // 참조 없이 각 인터페이스 구현 클래스의 go를 접근
  }
}

fun main() {
  val myDamas = CarModel("Damas 2010", VanImpl("100마력"))
  val my350z = CarModel("350Z 2008", SportImpl("350마력"))

  myDamas.carInfo()     //다형성 
  my350z.carInfo()
}

//Damas 2010 는 짐을 적재하며 100마력 마력을 가집니다.
//350Z 2008 는 경주용에 사용되며 350마력 마력을 가집니다.
~~~

#### 프로퍼티 위임과 by lazy
1. lazy 람다식은 람다식을 전달받아 저장된 Lazy<T> 인스턴스를 반환한다.
2. 최초 프로퍼티의 게터 실행은 lazy에 남겨진 람다식을 실행하고 결과를 기록한다.
3. 이후 프로퍼티 게터 실행은 이미 초기화되어 기록된 값을 반환한다.

* by lazy에 의한 초기화는 스레드에 좀 더 안정적으로 프로퍼티를 사용 할 수 있음

#### observable() 함수와 vetoable() 함수의 위임
    1. 코틀린에 표준 위임 구현 함수
    2. Delegates 를 임포트해야한다.
    * Observable - 변경이 일어날때마다 호출 처리
    * Vetoable - 반환값에 따라 프로퍼티 변경을 허용/취소가 가능

* Observable 예시
~~~kotlin
import kotlin.properties.Delegates

class User {
    // observable은 값의 변화를 감시하는 일종의 콜백 루틴
    var name: String by Delegates.observable("NONAME") {
            prop, old, new -> // 프로퍼티, 기존값, 새로운 값
        println("$old -> $new") // 이 부분은 이벤트가 발생할 때만 실행
    }
}

fun main() {
    val user = User()
    user.name = "Kildong" // 값이 변경되는 시점에서 첫 이벤트 발생
    user.name = "Dooly" // 값이 변경되는 시점에서 두 번째 이벤트 발생
}

//NONAME -> Kildong
//Kildong -> Dooly
~~~

* Vetoable 예시
    * 책에 notifydatasetchanged 호출하는 부분!! 안드로이드에서 쓸 듯
~~~kotlin
import kotlin.properties.Delegates

fun main() {

    var max: Int by Delegates.vetoable(0) { // 초기값은 0
            prop, old, new ->
        new > old // 조건에 맞지 않으면 거부권 행사
    }

    println(max) // 0
    max = 10
    println(max) // 10

    // 여기서는 기존값이 새 값보다 크므로 false 따라서 5를 재할당 하지 않음
    max = 5
    println(max) // 10
}
~~~

<br>

### 6-3. 정적 변수와 컴패니언 객체
    * 변수는 사용 범위에 따라 지역 변수 / 전역 변수로 나뉘어짐
    * 클래스는 인스턴스를 생성해 메모리에 동적으로 초기화해서 사용
    * 클래스의 메서드나 프로퍼티도 코드이 블록 영역에 따라 사용하는 범위가 결정됨
    * 동적인 초기화 없이 사용할 수 있는 방법 - 
    * 동적인 메모리에 할당/해제 되는 것이 아닌 프로그램을 실핼할 떄 고정적으로 가지는 메모리로 객체 생성없이 사용 가능
      1. 정적 변수 (Static Variable)
      2. 컴패니언 객체 (Companion Object)

#### 정적 변수와 컴패니언 객체
    * 일반적인 클래스의 객체 생성 없이 정적 변수나 메서드를 사용하면 프로그램 실행 시 메모리를 고정적으로 가진다.
      -> 인스턴스화 없이 사용 가능

#### 컴패니언 객체 사용하기
    * 코틀린에서 정적 변수를 사용할 떄 static 키워드가 없는 대신 컴패니언 객체를 제공
    * 실제 객체의 싱글톤으로 정의됨
    * 싱글톤 - 전역 변수를 사용하지 않고 객체를 하나만 생성하여, 생성된 객체를 어디에서든지 참조할 수 있도록 하는 디자인 패턴

~~~kotlin
class Person {
    var id: Int = 0
    var name: String = "Youngdeok"
    companion object { // 컴페니언 객체의 정의
        var language: String = "Korean"
        fun work() {
            println("working...")
        }
    }
}

fun main() {
    println(Person.language)  // 인스턴스를 생성하지 않고 기본값 사용
    Person.language = "English" // 기본값 변경 가능
    println(Person.language) // 변경된 내용 출력
    Person.work() // 메서드 실행
    //println(Person.name) // name은 companion object가 아니므로 오류
}

//Korean
//English
//working...
~~~

#### 코틀린에서 자바의 static 멤버 사용하기
    * 하기 예시 참고.
    * 자바 클래스를 먼저 만들고 접근

~~~java
// 자바의 Customer 클래스
public class Customer {
  public static final String LEVEL = "BASIC";  // static 필드
  public static void login() { // static 메서드
    System.out.println("Login...");
  }
}
~~~

~~~kotlin
// 코틀린에서 자바의 static 접근
fun main() {
    println(Customer.LEVEL)
    Customer.login()
}

//BASIC
//Login...
~~~

#### 자바에서 코틀린 컴패니언 객체 사용하기
    * @JvmStatic 애노테이션(annotation) 표기 사용 필요
    * 프로퍼티를 자바에서 사용하고자 할 경우에는 @JvmField 에노테이션 사용
    * 캠패니언 객체는 외부 클래스에서 private 프로퍼티에도 접근할 수 있기 때문에 유틸리티 클래스 등을 만드는 데 사용가능

~~~kotlin
class KCustomer {
  companion object {
    //const 컴파일 시간의 상수 - 컴파일 시간에 이미 값이 할당되는 것으로 자바에서 접근하기 위해 필요
    const val LEVEL = "INTERMEDIATE"
    @JvmStatic fun login() = println("Login...") // 어노테이션 표기 사용
    @JvmStatic val score = 3
    @JvmField val JOB = KJob()
  }
}

class KJob {
  var title: String = "Programmer"
}
~~~

~~~java
public class KCustomerAccess {
    public static void main(String[] args) {

        // 코틀린 코드의 KotlinFoo의 멤버를 접근
        System.out.println(KCustomer.LEVEL);
        KCustomer.login(); // 어노테이션을 사용할 때 접근 방법
        KCustomer.Companion.login(); // 위와 동일한 결과로 어노테이션을 사용하지 않을 때 접근 방법

        // KJob에 대한 객체 생성 후 접근
        KJob kjob = KCustomer.JOB;
        System.out.println(kjob.getTitle());

        // KCostomer를 통한 접근
        KCustomer.JOB.setTitle("Accountant");
        System.out.println(KCustomer.JOB.getTitle());
    }
}
~~~


#### 최상위 함수 사용하기

~~~kotlin
//@file:JvmName("PKLevel")
// 패키지 레벨 함수 혹은 최상위 함수라고 함
fun packageLevelFunc() {
    println("Package-Level Function")
}

fun main() {
    packageLevelFunc()
}

//Package-Level Function
~~~

~~~java
public class PackageLevelAccess {
    public static void main(String[] args) {

         PackageLevelFunctionKt.packageLevelFunc();
//        PKLevel.packageLevelFunc();
    }
}

//Package-Level Function
~~~

* @file:JvmName("PKLevel") 추가함으로써 하기 자바에서 PKLevel.packageLevelFunc(); 로 실행해야한다.

#### object와 싱글톤
    * object 선언 방식 - 접근 시점에 객체가 생성됨 (싱글톤 패턴에 이용)
    * 자바에서 Object선언에 접근하려면 INSTANCE를 사용하여 접근.

[object 선언과 컴패니언 객체 비교하기]
~~~
// 1. object 키워드를 사용한 방식
object OCustomer {
    var name = "Kildong"
    fun greeting() = println("Hello World!")
    val HOBBY = Hobby("Basketball")
    init {
        println("Init!")
    }
}

// 2. companion object를 사용한 방식
class CCustomer {
    companion object {
        const val HELLO = "hello"  // 상수 표현
        var name = "Joosol"
        @JvmField val HOBBY = Hobby("Football")
        @JvmStatic fun greeting() = println("Hello World!")
    }
}

class Hobby(val name: String)

fun main() {

    OCustomer.greeting()
    OCustomer.name = "Dooly"
    println("name = ${OCustomer.name}")
    println(OCustomer.HOBBY.name)

    CCustomer.greeting()
    println("name = ${CCustomer.name}, HELLO = ${CCustomer.HELLO}")
    println(CCustomer.HOBBY.name)
}
~~~

[자바에서 object 선언의 접근]
~~~java
public class OCustomerAccess {
    public static void main(String[] args) {
        String name = OCustomer.INSTANCE.getName(); // 코틀린의 object 선언 객체의 메서드 접근
        System.out.println(name);
    }
}
~~~

#### object 표현식
    * object 표현식은 object선언과 달리 이름이 없고 싱글톤이 아니다.
    * 사용할 때마다 새로운 인스턴스가 생성됨
    * 이름이 없는 익명 내부 클래스로 불리는 형태를 만들 수 있음

[object 표현식 사용해 보기 - 하위 클래스를 만들지 않고 특정 메서드를 오버라이딩]

~~~kotlin
open class Superman() {
    fun work() = println("Taking photos")
    fun talk() = println("Talking with people.")
    open fun fly() = println("Flying in the air.")
}

fun main() {
    val pretendedMan = object: Superman() { // object 표현식으로 fly()구현의 재설계
        override fun fly() = println("I'm not a real superman. I can't fly!")
    }
    pretendedMan.work()
    pretendedMan.talk()
    pretendedMan.fly()
}

//Taking photos
//Talking with people.
//I'm not a real superman. I can't fly!
~~~

