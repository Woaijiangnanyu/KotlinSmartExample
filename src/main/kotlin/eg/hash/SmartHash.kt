package eg.hash

/**
 * 给一个整数数组，找出两个数，使得他们的和等于目标target，并返回两个数下标
 */
fun twoSum(src: Array<Int>, target: Int): Pair<Int?, Int> {
    val hash = HashMap<Int?, Int>() // hash 存储
    for (i in src.indices) {
        if (hash.containsKey(src[i])) {
            return Pair(hash.get(src[i]), i)
        }
        hash.set(target - src[i], i)
    }
    return Pair(-1, -1)
}

/**
 * 连续数组
 * 给定一个二维数组，找到0和1数量相等的子数组最大长度
 * 使用sum来维护1，0数量的差值。在for循环中将差值插入hash表中，i为数组下标。当key sum在表中存在
 * i - preFix.get(sum)!! 表示此次位置出现的与hash.contain()中出现的1、0长度相等
 */
fun findMaxLen(src: Array<Int>): Int {
    val preFix = HashMap<Int, Int>()
    var sum = 0
    var max = 0
    preFix.put(0, -1)
    for (i in src.indices) {
        if (src[i] == 0) {
            sum--
        } else sum++

        if (preFix.containsKey(sum)) {
            max = Math.max(max, i - preFix.get(sum)!!)
        } else {
            preFix.put(sum, i)
        }
    }
    return max
}

/**
 *无重复元素子串最大长度
 * hash中存储每个元素的位置，设立一个左边界值，当hash中添加过重复元素，移动左边界值到开始出现重复元素位置
 */
fun lengthOfMaxLongSubString(str: String): Int {
    if (str.isNullOrEmpty()) {
        return 0
    }
    val hash = HashMap<Char, Int>()
    var start = -1
    var max = Int.MIN_VALUE
    for (i in str.indices) {
        if (hash.containsKey(str[i])) {
            val temp = hash.get(str[i])
            if (temp!! >= start) {
                start = temp
            }
        }
        hash.put(str[i], i)
        max = Math.max(max, i - start)// 计较是否重复添加后i - start 最大距离
    }
    return max
}


fun main() {
//    var num = arrayOf(1, 2, 3, 4, 5)
//    var (x, y) = twoSum(num, 5)
//    println("x=$x,y=$y")
//    var num = arrayOf(1, 1, 0, 1, 0, 0)
//    println(findMaxLen(num))
    var str = "ABACDEAD"
    println(lengthOfMaxLongSubString(str))
}