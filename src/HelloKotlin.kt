// as -> 유용해보인다.
import com.kotlin.chap02.Person as User

fun main() {
//    println("Hello Kotlin")

    val user1 = User("Kildong", 30)
    var user2 = Person("ganada", "Jihun")

    println(user1.name)
    println(user1.age)

    println(user2.id)
    println(user2.name)

}

class Person (val id: String, val name: String)