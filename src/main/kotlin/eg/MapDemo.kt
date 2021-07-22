package eg

fun main() {
    val list = IntArray(5) { it + 1 }
    val aa = list.reduce { acc, i ->
        acc + i
    }
    
    val Numbersequence = sequenceOf("four","three","two","one")
//    Numbersequence.first { it == "one" }?.let { println(it) }
//    Numbersequence.filter {
//        it.startsWith("t")
//    }.forEach(::println)
//    val aa = list.fold(0) { sum, element ->
//        println(sum)
//        sum + element
//    }
    println(aa)
    //asSequence 懒序列加载
//    list.asSequence().filter {
//        println("filter$it")
//        it % 2 == 0
//    }.map {
//        println("map$it")
//        it * 2 + 1
//    }.forEach(::println)
//    list.forEach(::println)
//    list.map (Int::toDouble).forEach(::println)
//    val list = arrayOf(
//        1..10,
//        20..30,
//        50..100
//    )
//
//    val mergeList = list.flatMap {
//        it.map {
//            "No.$it"
//        }
//    }
//
//    mergeList.forEach(::println)

}