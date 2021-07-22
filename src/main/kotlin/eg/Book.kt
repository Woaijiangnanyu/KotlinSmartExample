package eg

data class Book(var name: String, var price: Float) {
}

fun main() {
    val list = listOf<Book>(Book("aa",1f), Book("BB",2f), Book("VV",3f))
    list.maxBy(Book::price).let(::println)
//    var book = Book("Kotlin 从入门到放弃", 22f)
//    val (x, y) = book
//    println("x$x -- y$y")
//    var user = User()
//    user.age = 10
//    user.name  = "TOM"
//    println(user.age)
//    println(user.name)

}