package eg.array

import java.util.*

/**
 * 数字加一
 * 给定一个非负数，该数表示一个数组，该数加一，并返回一个数组，该数组高低位顺序排列
 * 999 -> [9,9,9]
 * 999 + 1 = [1,0,0,0]
 */
fun plusOne(num: Array<Int>): Array<Int> {
    var carries = 1
    val size = num.size
    var i = size - 1;
    run {
        while ((i >= 0) && (carries > 0)) {
            val sum = num[i] + carries
            num[i] = sum % 10
            carries = sum / 10
            i--
        }
    }
    if (carries == 0) {
        return num
    }
    var digits = arrayOfNulls<Int>(size + 1);
    digits as Array<Int>
    digits[0] = 1
    for (i in 1 until digits.size) {
        digits[i] = num[i - 1]
    }
    return digits
}

/**
 * 删除数组中指定的元素,并返回新数组大小
 */
fun delElement(src: Array<Int>?, elem: Int): Int {
    if (src.isNullOrEmpty()) return -1
    var index = 0
    for (i in src.indices) {
        if (src[i] != elem) {
            src[index++] = src[i]
        }
    }
    return index
}

/**
 *删除重复元素
 */
fun removeDuplicate(nums: Array<Int>?): Int {
    var src = nums
    if (src.isNullOrEmpty()) return -1
    var i = 0
    loop@ while (i != src!!.size) {
        if (i == src.size - 1) break
        for (k in i + 1 until src!!.size) {
            if (src[i] == src[k]) {
                var index = k // 第一个重复的元素
                for (j in index until src.size) {
                    if (j != src.size - 1) {
                        src[j] = src[j + 1]
                    } else {
                        src[j] = src[j]
                    }
                }
                src = Arrays.copyOf(src, src.size - 1) // 数组重新分配空间
                i = 0 // 重新开始遍历
                continue@loop //跳到外循环继续执行
            }
        }
        i++ // 不重复时i累加
    }
    return src.size
}

/**
 * 合并排序数组
 *
 */
fun mergeSortArray(srcA: Array<Int>, srcB: Array<Int>): Array<Int> {
    var a = srcA
    var b = srcB
    var i = a.size - 1
    var j = b.size - 1
    var index = a.size + b.size - 1
    //数组扩容
    a = Arrays.copyOf(a, a.size + b.size)
    while ((i >= 0) && (j >= 0)) {
        if (a[i] > b[j]) {
            a[index--] = a[i--]
        } else a[index--] = b[j--]
    }
    while (i >= 0) {
        a[index--] = a[i--]
    }

    while (j >= 0) {
        a[index--] = a[j--]
    }
    return a
}

/**
 * 最大子数组的和
 * sum 累加数组之和，如果累加得到的数小于0，则sum赋值0，重新开始累加
 */
fun maxSubArraySum(src:Array<Int>?):Int{
    if(src.isNullOrEmpty()) return Int.MIN_VALUE
    var max = Int.MIN_VALUE
    var sum = 0
    for (value in src){
        sum += value
        max = Math.max(sum,max)
        sum = Math.max(sum,0)
    }
    return max
}

/***
 * 找到数组中的主元素
 * 主元素：在数组中出现的次数严格意义上大于数组元素个数二分之一
 */
fun findMajorElement(src: Array<Int>):Int{
    var count = 0
    var numMajor = 0
    for (num in src){
        if (count == 0){
            numMajor = num
        }
        if (num == numMajor){
            count++
        }else count--
    }
    return numMajor
}
fun main() {
//    var numStr = arrayOf(9, 9, 9)
//    plusOne(numStr).forEach { println(it) }
//    println(delElement(numStr,9))
//    val src = arrayOf(1, 2, 1, 3)
//    println(removeDuplicate(src))
//    var srcA = arrayOf(1,3,5,7,9)
//    val srcB = arrayOf(2,4,6,8,10)
//    mergeSortArray(srcA,srcB).forEach(::println)
//    var nums = arrayOf(1,-1,1,-1,1,1,-1)
//    println(maxSubArraySum(nums))
    var nums = arrayOf(1,2,3,1,1,1,1,2,1)
    println(findMajorElement(nums))
}