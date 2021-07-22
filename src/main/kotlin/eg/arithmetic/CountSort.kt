package eg.arithmetic

fun CountSort(values: IntArray): IntArray {

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
    for (i in result.indices){
        while (result[i]>0){
            b[index++] = i
            result[i]--
        }
    }
    //返回排序完成后的数组
    return b
}

fun main() {
    val nums = intArrayOf(0,2,5,3,7,9,10,3,7,6)
    CountSort(nums).forEach(::println)
}