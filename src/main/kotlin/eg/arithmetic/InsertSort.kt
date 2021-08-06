package eg.arithmetic

/**
 * 最好O(n) 最坏 O(n²)
 * 稳定排序
 */
fun insertSort(A: Array<Int>): Array<Int> {
    for (i in 1 until A.size) {
        var j = i
        while ((j > 0) && (A[j] < A[j - 1])) {
            var temp = A[j - 1]
            A[j - 1] = A[j]
            A[j] = temp
            j--
        }
    }
    return A
}

fun main() {
    insertSort(arrayOf(9,3,6,4,7,1,4)).forEach(::println)
}