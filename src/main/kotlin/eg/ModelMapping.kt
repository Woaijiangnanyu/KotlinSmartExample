package eg

import java.lang.IllegalArgumentException
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor


inline fun <reified From : Any, reified To : Any> From.mapAs(): To {
    return From::class.memberProperties.map { it.name to it.get(this) }.toMap().mapAs()
}

inline fun <reified To : Any> Map<String, Any?>.mapAs(): To {
    return To::class.primaryConstructor!!.let { it ->
        it.parameters.map { parameter ->
            parameter to (this[parameter.name]
                ?: if (parameter.type.isMarkedNullable) null else throw IllegalArgumentException("${parameter.name}is required but missing "))
        }.toMap().let(it::callBy)
    }
}

//mapping fields. Using Annotations next chapter.
data class UserVO(val login: String, val avatarUrl: String)

data class UserDT(
    var id: Int,
    var login: String,
    var avatarUrl: String,
    var url: String,
    var htmlUrl: String
)

fun main() {
    val userDT = UserDT(
        0,
        "Bennyhuo",
        "https://avatars2.githubusercontent.com/u/30511713?v=4",
        "https://api.github.com/users/bennyhuo",
        "https://github.com/bennyhuo"
    )

    val userVO: UserVO = userDT.mapAs()
    println(userVO)

    val userMap = mapOf(
        "id" to 0,
        "login" to "Bennyhuo",
        "avatarUrl" to "https://api.github.com/users/bennyhuo",
        "url" to "https://api.github.com/users/bennyhuo"
    )

    val userVOFromMap: UserVO = userMap.mapAs()
    println(userVOFromMap)
}