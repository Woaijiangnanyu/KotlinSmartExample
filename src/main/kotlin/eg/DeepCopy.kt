package eg

import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

data class Group(val id: Int, val name: String, val location: String)

data class Person(val id: Int, val name: String, val group: Group)

fun <T : Any> T.deepCopy(): T {
    if (!this::class.isData) return this

    return this::class.primaryConstructor!!.let { primaryConstructor ->
        primaryConstructor.parameters.map { kParameter ->
            val value = (this::class as KClass<T>).memberProperties.first { it.name == kParameter.name }
                .get(this)
            if ((kParameter.type.classifier as? KClass<*>)?.isData == true) {
                kParameter to value?.deepCopy()
            } else {
                kParameter to value
            }
        }.toMap()
            .let(primaryConstructor::callBy)
    }
}

fun main() {
    val person = Person(
        0,
        "Bennyhuo",
        Group(
            0,
            "Kotliner.cn",
            "China"
        )
    )

    val copiedPerson = person.copy()
    val deepCopiedPerson = person.deepCopy()

    println(person === copiedPerson) //false
    println(person === deepCopiedPerson) //false

    println(person.group === copiedPerson.group) //true for shallow copy.
    println(person.group === deepCopiedPerson.group) //false

    println(deepCopiedPerson)
}