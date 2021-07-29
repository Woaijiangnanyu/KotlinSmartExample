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

fun main() {
    var num = arrayOf(1,2,3,4,5)
    var (x,y) = twoSum(num,5)
    println("x=$x,y=$y")
}