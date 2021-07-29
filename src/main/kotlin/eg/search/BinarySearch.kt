package eg.search

import eg.arithmetic.basketSort

/**
 *二分查找（针对有序数列）
 * -1 代表没有找到
 *
 **/
fun binarySearch(src: Array<Int>, start: Int, end: Int, key: Int): Int {
    if (start > end) return -1
    var mid = (end - start) / 2 + start
    if (src[mid] > key) {
        mid = binarySearch(src, start, mid - 1, key)
    } else if (src[mid] < key) {
        mid = binarySearch(src, mid + 1, end, key)
    }
    return mid
}

fun main() {
    var number = arrayOf(1, 3, 4, 6, 8, 9)
    println(binarySearch(number, 0, number.size - 1, 9))
}