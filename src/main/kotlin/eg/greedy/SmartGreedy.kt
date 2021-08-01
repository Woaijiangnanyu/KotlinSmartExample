package eg.greedy

/**
 * 贪心算法
 * 买卖股票最佳时机
 * 假设有一个数组，它的第i个元素是一支给定的股票在第i天的价格。
 * 如果你最多只允许完成一次交易(例如，一次买卖股票)，设计一个算法来找出最大利润。
 */
fun maxProfit(prices: Array<Float>): Float {
    var profit: Float = 0F
    var min = Float.MAX_VALUE
    for (price in prices) {
        min = Math.min(min, price)
        profit = Math.max(price - min, profit)
        println("min:$min")
    }
    return profit
}

/**
 * 贪心算法
 * 买卖股票最佳时机II
 * 给定一个数组 prices 表示一支股票每天的价格。
 * 可以完成任意次数的交易, 不能同时参与多个交易，设计一个算法求出最大的利润。
 * 贪心：只要相邻的两天股票的价格是上升的, 我们就进行一次交易, 获得一定利润
 */
fun maxProfit2(prices: Array<Float>): Float {
    var profit: Float = 0F
    for (i in 0 until prices.size - 1) {
        var diff = prices[i + 1] - prices[i]
        if (diff > 0) {
            profit += diff
        }
    }
    return profit
}

fun main() {
    val prices = arrayOf(12.3F,14.9F,12.0F,3.0F,6.6F)
    println(maxProfit(prices))
}