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
    if (steps.size > 1) {
        steps[1] = 1
    }
    for (i in 2 until steps.size) {
        steps[i] = steps[i - 1]!! + steps[i - 2]!!
    }
    return steps[n]!!
}

/**
 * 打劫房屋钱财
 * 假设你是一个专业的窃贼，准备沿着一条街打劫房屋。
 * 每个房子都存放着特定金额的钱。
 * 你面临的唯一约束条件是：相邻的房子装着相互联系的防盗系统，且 当相邻的两个房子同一天被打劫时，该系统会自动报警。
 * 给定一个非负整数列表，表示每个房子中存放的钱， 算一算，如果今晚去打劫，在不触动报警装置的情况下, 你最多可以得到多少钱 。
 * 由抢房屋的性质可以看出，
 * 抢前i个房屋能得到的最大值，与后面如何抢的方案无关，只与前i - 1个房屋的最优方案有关。
 * 这满足了动态规划的无后效性和最优子结构。
 * 同时，由于题目不能抢相邻房屋，那么如果抢了第i个房屋，就不能抢第i - 1个房屋，可以得出前i个的最优方案也与前i - 2个的最优方案有关
 */
fun houseRobber(A: Array<Int>?): Int {
    if (A.isNullOrEmpty()) return 0
    val profit = arrayOfNulls<Int>(A.size + 1)
    profit[0] = 0
    profit[1] = A[0]
    for (i in 2 until profit.size){
        profit[i] = Math.max((profit[i - 2]!! + A[i-1]),profit[i-1]!!)
    }
    return profit[A.size]!!
}

fun main() {
    println(wordBreak("AABAA", setOf("AA", "AB", "A")))
}