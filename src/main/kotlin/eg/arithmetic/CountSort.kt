package eg.arithmetic

/**
 * 计数排序基础版
 */
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
    val count = IntArray(max - min + 1)
    for (num in values) {
        count[num - min] += 1
    }

    //
    for (i in 1 until count.size) {
        count[i] += count[i - 1]
    }

    val result = IntArray(values.size)
    for (j in (values.size - 1) downTo 0) {
        //var aa = values[j] //当前待排数据
        //var index = count[values[j] - min] - 1 //加总数组中对应values中元素下标
        //result[index] = aa  //将数据存入对应的下标中
        //count[values[j] - min]-- //加总数组中，该值减一
        //缩写成一下形式
        result[--count[values[j] - min]] = values[j]
    }

    return result
}

/**
 * 基于计数排序实现
 * 比较两个数组，是否数组A全部包含数组B，假设数组A大，数组B小
 * O(n)
 */
fun compareTwoArray(A: Array<Int>, B: Array<Int>): Boolean {
    //首先找到数组A的最大值
    var max = Int.MIN_VALUE
    for (i in A.indices) {
        max = Math.max(max, A[i])
    }
    // 赋值数组大小 max+1
    val array = Array<Int>(max + 1) { 0 }
    //统计A数组中元素出现次数
    for (i in A.indices) {
        array[A[i]] += 1
    }
    //记录B中在A中出现的次数
    var count = 0
    for (i in B.indices) {
        if (B[i] >= max + 1) {
            break
        }
        if (A[B[i]] > 0) {
            count++
        }
    }
    //B中元素全部出现在A中会有count == B.size的情况
    return count == B.size
}

fun main() {
//    val nums = intArrayOf(0, 2, 5, 3, 7, 9, 10, 3, 7, 6)
//    val nums = intArrayOf(0,1,100)
//    CountSort01(nums).forEach(::println)
//    CountSort(nums).forEach(::println)
//    println(1 % 10)
    println(compareTwoArray(arrayOf(1, 2, 3, 4, 5), arrayOf(2, 3, 4)))
}