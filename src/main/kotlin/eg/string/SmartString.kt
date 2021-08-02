package eg.string

/**
 * 生成括号组合
 *
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

fun main() {
    generateParenthesis(3)?.forEach(::println)
}