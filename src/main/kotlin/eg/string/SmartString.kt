package eg.string

import gjl.utils.SmartSort
import java.lang.StringBuilder

/**
 * 生成括号组合
 * 给定n对，生成所有有效的括号组合
 */
fun generateParenthesis(n: Int): MutableList<String>? {
    if (n <= 0) return null
    var src = mutableListOf<String>()
    generateHelper(n, n, "", src)
    return src
}

fun generateHelper(nL: Int, nR: Int, parenthesis: String, src: MutableList<String>) {

    if ((nL < 0) or (nR < 0)) {
        return
    }

    if ((nL == 0) and (nR == 0)) {
        src.add(parenthesis)
    }

    generateHelper(nL - 1, nR, "$parenthesis(", src)

    if (nL >= nR) return // 左括号数比待排剩余括号少

    generateHelper(nL, nR - 1, "$parenthesis)", src)

}

/**
 * Excel表列标题
 * 给定一个正整数，返回相应的列标题，如Excel表中所示。
 * 如1 -> A，2 -> B...26 -> Z，27 -> AA
 */
fun convertToTitle(num: Int): String {
    var n = num
    val str = StringBuilder()
    while (n > 0) {
        n--
        str.append('A'.plus(n % 26))
        n /= 26
    }
    return str.reverse().toString()
}

/**
 * 字符串翻转
 * 初始状态：++++++-+
 * --++++-+
 * +--+++-+
 * ++--++-+
 * +++--+-+
 * ++++---+
 */
fun generatePossibleNextMoves(str: String): MutableList<String> {
    var list = mutableListOf<String>()
    var i = -1
    i = str.indexOf("++", i + 1)
    while (i >= 0) {
        list.add("${str.substring(0, i)}--${str.substring(i + 2)}")
        i = str.indexOf("++", i + 1)
    }
    return list
}


/**
 * 翻转字符串中的单词
 */
fun reverseWord(src: String): String {
    var split = src.split(" ")
    var strBuilder = StringBuilder()
    for (i in split.size - 1 downTo 0) {
        if (!strBuilder.isNullOrEmpty()) {
            strBuilder.append(" ")
        }
        strBuilder.append(split[i])
    }
    return strBuilder.toString()
}

fun main() {
//    generateParenthesis(3)?.forEach(::println)
//    println(convertToTitle(27))
//    var ss = SmartSort()
//    println(ss.convertToTitle(28))
//    var ss = "++++++-+"
//    generatePossibleNextMoves(ss).forEach(::println)
    var ss = "A B C Hello World"
    println(reverseWord(ss))
}