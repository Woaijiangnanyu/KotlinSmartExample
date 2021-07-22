package eg.arithmetic

/**
 * 分治策略
 * 归并排序
 * 时间复杂度 O(N)
 */
fun mergeSort(values: IntArray, l: Int, h: Int): IntArray {
    if (l == h) {
        return intArrayOf(values[l])
    }
    val middle = l + (h - l) / 2
    val left = mergeSort(values, l, middle) // 左数组
    val right = mergeSort(values, middle + 1, h) //右数组
    val newArr = IntArray(left.size + right.size)  //合并后新数组
    var m = 0
    var i = 0
    var j = 0
    while ((i < left.size) and (j < right.size)) {  // 合并集合排序
        newArr[m++] = if (left[i] < right[j]) {
            left[i++]
        } else {
            right[j++]
        }
    }
    while (i in left.indices) { //查询剩余元素添加到集合
        newArr[m++] = left[i++]
    }
    while (j in right.indices) { //查询剩余元素添加到集合
        newArr[m++] = right[j++]
    }
    return newArr
}

fun main() {
    val arrs = intArrayOf(2, 5, 34, 11, 44, 56, 22, 7, 9, 66, 0)
    mergeSort(arrs, 0, arrs.size - 1).forEach(::println)
}