package eg.queue

import java.util.*

/**
 * 前K大的数
 */
fun topK(src: Array<Int>?, key: Int): Int {
    if (src.isNullOrEmpty()) return Int.MIN_VALUE
    val priority = PriorityQueue<Int>() // 根据优先级排序
    for (value in src) {
        priority.add(value)
        if (priority.size > key) {
            val a = priority.poll()
            println(a)
        }
    }
    return priority.peek()
}

/***
 * 第K大的数II
 */

fun kthLargeElement(src: Array<Int>, key: Int): Int {
    if (src.isNullOrEmpty() or (key < 1) or (key > src.size)) return -1
    return partitionElement(src, 0, src.size - 1, src.size - key)
}

/**
 *划分区域
 */
fun partitionElement(src: Array<Int>, start: Int, end: Int, key: Int): Int {
    if (start >= end) return src[key]
    var left = start
    var right = end
    val pivot = src[(end + start) / 2]
    while (left <= right) {
        while ((left <= right) and (src[left] < pivot)) {
            left++
        }
        while ((left <= right) and (src[right] > pivot)) {
            right--
        }
        if (left <= right) {
            swapeElement(src, left, right)
            left++
            right--
        }
    }
    if (key <= right) {
        return partitionElement(src, start, right, key)
    }

    if (key >= left) {
        return partitionElement(src, left, end, key)
    }
    return src[key]
}

fun swapeElement(src: Array<Int>, i: Int, j: Int) {
    val temp = src[j]
    src[j] = src[i]
    src[i] = temp
}

fun main() {
//    var nums = arrayOf(1, 2, 4, 5, 3, 1, 9, 5, 7)
//    println(topK(nums, 5))
    var nums = arrayOf(1, 3, 6, 2, 5, 4, 7)
    println(kthLargeElement(nums, 3))
}