package eg.hash

import kotlin.math.max

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

class HashPoint(var x: Int = 0, var y: Int = 0)

/**
 *最多点在同一条直线上
 * y = kx + b
 * k斜率  k = (y2-y1)/(x2-x1)
 * b纵截距  b = (x2*y1 - x1*y2)/(x2-x1)
 */
fun maxPoints(points: Array<HashPoint>): Int {
    var overLap = 0
    var maxPoint = 0
    val hash = HashMap<String, Int>()
    if (points.isNullOrEmpty()) {
        return 0
    }
    for (i in points.indices) {
        for (j in i  until points.size - 1 ) {
            println("ponits[$i] = (${points[i].x},${points[i].y})")
            println("ponits[${j+1}] = (${points[j+1].x},${points[j+1].y})")
            if ((points[i].x == points[j+1].x) and (points[i].y == points[j+1].y)) {
                overLap++
                continue
            }
            var isSpecial = false
            var tag = ""
            // 考虑垂直横坐标情况
            //考虑平行横坐标情况
            when {
                (points[i].x == points[j+1].x) and (points[i].y != points[j+1].y) -> {
                    isSpecial = true
                    tag = "|"
                }
                (points[i].y == points[j+1].y) and (points[i].x != points[j+1].x) -> {
                    isSpecial = true
                    tag = "-"
                }
                else -> isSpecial = false
            }
            if (!isSpecial) {
                val k: Double = (points[j+1].y - points[i].y).toDouble() / (points[j+1].x - points[i].x).toDouble()
                val b =
                    (points[j+1].x * points[i].y - points[i].x * points[j+1].y).toDouble() / (points[j+1].x - points[i].x).toDouble()
                tag = k.toString() + "-" + b.toString() + "-" +i  // 单层上共线最多
//                println("k:$k  b:$b tag:$tag")
            }
            if (hash.contains(tag)) {
                println("contains - hash.put($tag,${hash.getValue(tag)+1})")
                hash.put(tag, hash.getValue(tag) + 1)
            } else {
                println("hash.put($tag,2)")
                hash.put(tag, 2)
            }
            maxPoint = Math.max(maxPoint, hash.getValue(tag))
            println("maxPoint:$maxPoint")
        }
        maxPoint = Math.max(overLap,maxPoint)
    }
    return maxPoint
}

fun bubblePoint(src: Array<HashPoint>): Array<HashPoint> {
    for (i in src.indices) {
        for (j in 0 until src.size - i - 1) {
            if ((src[j].x > src[j+1].x)
                or ((src[j].x == src[j+1].x) and (src[j].y > src[j+1].y))) {
                var temp = src[j+1]
                src[j+1] = src[j]
                src[j] = temp
            }
        }
    }
    return src
}

fun main() {
//    var num = arrayOf(1, 2, 3, 4, 5)
//    var (x, y) = twoSum(num, 5)
//    println("x=$x,y=$y")
//    var num = arrayOf(1, 1, 0, 1, 0, 0)
//    println(findMaxLen(num))
//    var str = "ABACDEAD"
//    println(lengthOfMaxLongSubString(str))
    var src =
        arrayOf( HashPoint(1, 4), HashPoint(2, 5), HashPoint(3, 6), HashPoint(4, 8), HashPoint(6, 11), HashPoint(8, 14), HashPoint(4, 7), HashPoint(5, 8))
//    bubblePoint(src).forEach {
//        println("(${it.x},${it.y})")
//    }
    println(maxPoints(bubblePoint(src)))
}