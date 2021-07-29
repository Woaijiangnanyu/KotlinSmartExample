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
        } else if (")]}".contains(c)) {
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

class StackQueue<T> {
    var inStack = Stack<T>();
    var outStack = Stack<T>()

    private fun in2outStack() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop())
        }
    }

    fun push(value: T) {
        inStack.push(value)
    }

    fun pop(): T {
        if (!inStack.isEmpty()) {
            in2outStack()
        }
        return outStack.pop()
    }

    fun top(): T {
        if (!inStack.isEmpty()) {
            in2outStack()
        }
        return outStack.peek()
    }
}

fun main() {
//    var s = "(abc)a{(bb)[1][1][3]}"
//    var s1 = "(abc{)}{(bb)}1][1][3]}"
//    println(isValidParentheses(s))

    var queue = StackQueue<Int>()
    queue.push(1)
    queue.push(2)
    queue.push(3)
    queue.push(4)
    queue.push(5)
    println(queue.pop())
    println(queue.top())
}

