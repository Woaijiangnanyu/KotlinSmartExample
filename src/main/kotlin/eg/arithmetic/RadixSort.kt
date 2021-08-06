package eg.arithmetic

/**
 * 基数排序
 */
fun radixSort(A: Array<Int>): Array<Int> {
    val n = A.size
    var radix = 1
    val temp = Array<Int>(n) { 0 }
    val count = Array<Int>(10) { 0 }
    var k: Int
    for (i in 0 until maxBit(A)) {
        for (j in 0 until count.size) { // 清空计数器
            count[j] = 0
        }
        for (j in 0 until n) {
            k = A[j] / radix % 10
            count[k]++
        }
        for (j in 1 until count.size) { //记录A位置到count数组
            count[j] += count[j - 1]
        }
        for (j in A.size - 1 downTo 0) {
            k = A[j] / radix % 10
            temp[count[k] - 1] = A[j]
            count[k]--
        }
        for (j in A.indices) {
            A[j] = temp[j]
        }
        radix *= 10
    }
    return A
}

/**
 * 获取最大位数
 */
fun maxBit(A: Array<Int>): Int {
    var max = A[0]
    for (value in A) {
        max = Math.max(value, max)
    }
    var d = 1
    var p = 1
    while (max / p >= 10) {
        p *= 10
        d++
    }
    return d
}

fun main() {
    radixSort(arrayOf(53, 3, 542, 748, 14, 214, 154, 63, 616)).forEach(::println)
}