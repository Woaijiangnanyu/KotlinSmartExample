package eg.arithmetic

/**
 * 选择排序
 * ① 第一轮从下标为 1 到下标为 n-1 的元素中选取最小值，若小于第一个数，则交换
 * ② 第二轮从下标为 2 到下标为 n-1 的元素中选取最小值，若小于第二个数，则交换
 * ③ 依次类推下去……
 * 时间复杂度O(n²)
 * 空间复杂度O(1)
 * 不稳定排序
 */
fun selectSort(A: Array<Int>): Array<Int> {
    var min: Int
    for (i in 0 until A.size) {
        min = i
        for (j in i + 1 until A.size) {
            if (A[j] < A[min]) {
                min = j
            }
        }
        var temp = A[min]
        A[min] = A[i]
        A[i] = temp
    }
    return A
}

fun main() {
    selectSort(arrayOf(20, 40, 30, 10, 60, 50)).forEach(::println)
}