package eg.dynamic

/**
 * 单词拆解
 * 给定字符串str和字典，str是否可以分割成一个或者多个以空格分割的子串，并且子串都在字典中
 */
fun wordBreak(str: String, dict: Set<String>): Boolean {
    var maxLength = getMaxLength(dict)
    val wordBre = Array<Boolean>(str.length + 1) { it != it }

    //当字符串为空时，认为可以分割
    wordBre[0] = true
    for (i in 1 until wordBre.size) {
        var j = 1
        while ((j <= maxLength) && (j <= i)) {
            var word = str.substring(i - j, i)
            //字典中是否包含相关字符，同时判断str中前个是否能够分割
            if (dict.contains(word) && wordBre[i - j]) {
                wordBre[i] = true
                break
            }
            j++
        }
    }
    return wordBre[wordBre.size - 1]
}

/**
 * 获取字典中单词的最长长度
 */
fun getMaxLength(dict: Set<String>): Int {
    var max = 0
    for (value in dict) {
        max = Math.max(max, value.length)
    }
    return max
}

/**
 * 爬楼梯，设有n节台阶，每次直走一步或两步，求最多有几种走法
 */
fun climbStairs(n: Int): Int {
    if (n == 0) return 0
    //
    // 拆分问题
    // 每个阶段结果都对下个阶段产生影响，最终一个阶段的结果就是我们要求解的结果
    // 假设将台阶划分成 n + 1 个阶段（开始和结束无非就是一步或者两步）
    var steps = arrayOfNulls<Int>(n + 1)
    steps[0] = 1
    if (steps.size > 1){
        steps[1] = 1
    }
    for (i in 2 until steps.size){
        steps[i] = steps[i-1]!! + steps[i-2]!!
    }
    return steps[n]!!
}

fun main() {
    println(wordBreak("AABAA", setOf("AA", "AB", "A")))
}