package eg.arithmetic

import java.util.*

fun basketSort(values: IntArray): IntArray {
    var max = Int.MIN_VALUE
    var min = Int.MAX_VALUE
    for (num in values) {
        max = Math.max(num, max)
        min = Math.min(num, min)
    }
    //计算桶个数
    val count = (max - min) / values.size + 1
    //创建一个二维数组
    var basket = mutableListOf<MutableList<Int>>()

    for (i in 0..count) {
        basket.add(i, mutableListOf())
    }
    for (num in values) {
        basket[(num-min).div(values.size)].add(num)
    }

    for (i in basket.indices){
        basket.run { get(i).sort() }
    }
    var index = 0
    for (i in basket.indices){
        for (j in basket.get(i).indices){
            values[index++] = basket[i][j]
        }
    }
    return values
}

fun main() {
    val nums = intArrayOf(0, 2, 5, 3, 7, 9, 10, 3, 7, 6)
    basketSort(nums).forEach(::println)
}
