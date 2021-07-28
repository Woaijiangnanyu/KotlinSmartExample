package eg.stack

import java.util.*

/**
 * 实现一个栈，实现方法min()获取栈内最小元素
 */
class MinStack() {
    private var stack: Stack<Int> = Stack()
    private var minStack: Stack<Int> = Stack()

    fun push(value: Int) {
        stack.push(value)
        if (minStack.isEmpty()) {
            minStack.push(value)
        } else minStack.push(Math.min(value, minStack.pop()))
    }

    fun pop(): Int {
        minStack.pop()
        return stack.pop()
    }

    fun min(): Int {
        return minStack.peek()
    }
}

fun main() {
    val stack = MinStack()
    stack.push(1)
    stack.push(2)
    stack.push(3)
    println("最小值：${stack.min()}")
    println("弹出值：${stack.pop()}")
}