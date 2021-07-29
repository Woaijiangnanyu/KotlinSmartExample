package eg.stack

import java.util.*

/**
 * 给定一个字符串表示括号序列，()(){}[and] 代表有效括号序列 [(])
 */
fun isValidParentheses(s: String): Boolean {
    var stack = Stack<Char>()
    if (s.isNullOrBlank()) false
    for (c in s.toCharArray()) {
        if ("({[".contains(c)) {
            stack.push(c)
        } else if (")]}".contains(c)){
            if (!stack.isEmpty() && isValid(stack.peek(), c)) {
                stack.pop()
            } else return false
        }
    }
    return stack.isEmpty()
}

fun isValid(a: Char, b: Char): Boolean {
    return (a == '(' && b == ')') || (a == '[' && b == ']') || (a == '{' && b == '}')
}

fun main() {
    var s = "(abc)a{(bb)[1][1][3]}"
    var s1 = "(abc{)}{(bb)}1][1][3]}"
    println(isValidParentheses(s))
}