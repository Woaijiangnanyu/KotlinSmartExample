package eg.string

import gjl.utils.SmartSort
import java.io.File
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

    if ((nL < 0) || (nR < 0)) {
        return
    }

    if ((nL == 0) && (nR == 0)) {
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

/**
 *最长公共前缀
 */
fun longestCommonPrefix(src: Array<String>?): String {
    if (src.isNullOrEmpty()) return ""
    var prefix = src[0]
    for (i in 1 until src.size) {
        var j = 0
        while (!(!(j < src[i].length) || !(j < prefix.length) || !(src[i][j] == prefix[j]))) {
            j++
        }
        if (j == 0) return ""
        prefix = src[i].substring(0, j)
    }
    return prefix
}

/**
 * 数字判断是否是回文
 * （翻转180`后和原先保持一致）
 */
fun palindromdNumber(num: Int): Boolean {
    var n = num
    if (n < 0) return false
    var div = 1
    while (n.div(div) >= 10) { // 找到初始的除数
        div *= 10
    }
    while (n > 0) {
        if (n.div(div) != n.rem(10)) {
            return false
        }
        n = (n.rem(div).div(10))
        div = div.div(100)
    }
    return true
}

/**
 * 翻转整数
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）
 * 120 ->0
 * 123 -> 321
 */
fun reverseInteger(num: Int): Int {
    var n = num
    var reversed_n = 0
    while (n != 0) {
        var temp = reversed_n.times(10) + n.rem(10)//rem取模
        n /= 10
        if (temp.div(10) != reversed_n){
            reversed_n = 0
            break
        }
        reversed_n = temp
    }
    return reversed_n
}

/**
 * 给定一个文件，统计每个字符个数（不包括空格），以字符和字符个数分组输出
 */
fun stringGroup(){
    File("build.gradle").readText()
        .toCharArray()
        .filterNot(Char::isWhitespace)
        .groupBy {
            it }
        .map {
            it.key to it.value.size
        }.let(::println)
}

fun main() {
//    generateParenthesis(3)?.forEach(::println)
//    println(convertToTitle(27))
//    var ss = SmartSort()
//    println(ss.convertToTitle(28))
//    var ss = "++++++-+"
//    generatePossibleNextMoves(ss).forEach(::println)
//    var ss = "A B C Hello World"
//    println(reverseWord(ss))
//    var ss = arrayOf("AAB", "AAC", "AAAD")
//    println(longestCommonPrefix(ss))
//    println(palindromdNumber(1231))
//    println(reverseInteger(120))
    stringGroup()
}