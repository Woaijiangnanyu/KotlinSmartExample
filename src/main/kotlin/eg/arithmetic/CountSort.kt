package eg.arithmetic

fun CountSort01(values: IntArray): IntArray {

    //找出values数组中的最大值
    var max = Int.MIN_VALUE
    for (num in values) {
        max = Math.max(max, num)
    }
    //初始化最大数组空间为最大值+1
    val result = IntArray(max + 1)
    //对计数数组根据目标元素出现个数赋值
    for (num in values) {
        result[num] += 1
    }
    val b = IntArray(values.size)
    var index = 0
    //遍历数组，将计数数组的索引值填充到新建数组中
    for (i in result.indices) {
        while (result[i] > 0) {
            b[index++] = i
            result[i]--
        }
    }
    //返回排序完成后的数组
    return b
}

/**
 * 计数排序进阶版
 * 优化 计数数组大小
 *
 */
fun CountSort(values: IntArray): IntArray {
    var max = Int.MIN_VALUE
    var min = Int.MAX_VALUE
    for (num in values) {
        max = Math.max(num, max)
        min = Math.min(num, min)
    }

    // 优化计数数组大小
    var count = IntArray(max - min + 1)
    for (num in values) {
        count[num - min] += 1
    }

    //
    for (i in 1 until count.size) {
        count[i] += count[i - 1]
        println("count[$i] = ${count[i]}")
    }

    val result = IntArray(values.size)
    for (j in (values.size - 1) downTo 0) {
        result[--count[values[j] - min]] = values[j]
    }

    return result
}

fun main() {
//    val nums = intArrayOf(0, 2, 5, 3, 7, 9, 10, 3, 7, 6)
//    val nums = intArrayOf(0,1,100)
//    CountSort01(nums).forEach(::println)
//    CountSort(nums).forEach(::println)
//    println(1 % 10)
}