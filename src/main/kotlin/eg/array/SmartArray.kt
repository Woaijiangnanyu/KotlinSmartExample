package eg.array

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
        while ((i >= 0) and (carries > 0)) {
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
fun delElement(src:Array<Int>?,elem:Int):Int{
    if (src.isNullOrEmpty()) return -1
    var index = 0
    for (i in src.indices){
        if (src[i] != elem){
            src[index++] = src[i]
        }
    }
    return index
}

fun main() {
    var numStr = arrayOf(9,9,9)
//    plusOne(numStr).forEach { println(it) }
    println(delElement(numStr,9))
}