package eg

enum class StateEnum : Runnable {
    Idle, Busy;

    override fun run() {
        println("this enum test .. ")
    }
}

fun main() {
//    println(StateEnum.Idle.next().name)
//    println(StateEnum.Busy.next().name)
//    IntArray(5){it + 1}.forEach {
//        println(it)
//    }
    val colorRange = Color.White..Color.Black
    val color = Color.Blue
    println(color in colorRange)
}

fun StateEnum.next(): StateEnum {
    return StateEnum.values().let {
        val nextOrdinal = (ordinal + 1) % it.size
        println("nextOrdinal $nextOrdinal")
        it[nextOrdinal]
    }
}

enum class Color {
    White, Red, Green, Blue, Yellow, Black
}