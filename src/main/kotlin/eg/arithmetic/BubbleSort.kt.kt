package eg.arithmetic

/**
 * 冒泡排序
 * 时间复杂度 O(n2)
 *
 */
fun bubbleSort(vararg values: Int): IntArray {
    for (i in values.indices) {
        for (j in 0 until values.size - i - 1){
            if (values[j] > values[j+1]){
                var temp = values[j+1]
                values[j+1] = values[j]
                values[j] = temp
            }
        }
    }
    return values
}

fun main() {
    bubbleSort(1,23,3,45,32,1,100,56).forEach(::println)
}