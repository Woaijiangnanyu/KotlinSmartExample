package eg.search

import eg.arithmetic.basketSort
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

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

/**
 * 求一个正整数的平方根
 */
fun sqrt(num: Int): Int {
    if (num <= 0) throw IllegalArgumentException("argument is nvalid")
    if (num <= 1) {
        return num
    }
    var start = 1
    var end = num
    while (start + 1 < end) {
        var middle = (end - start) / 2 + start // 二分查找
        when {
            middle == num / middle -> return middle // 恰好开平方
            middle > num / middle -> end = middle // 不会再有比middle大的数平方却比num还要小，num平方根要比middle小
            else -> start = middle
        }
    }
    if (end > num / end){
        return start
    }
    return end
}

fun main() {
//    var number = arrayOf(1, 3, 4, 6, 8, 9)
//    println(binarySearch(number, 0, number.size - 1, 9))

    println(sqrt(6))
}