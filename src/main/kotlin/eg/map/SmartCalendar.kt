package eg.map

import java.util.*

/**
 * 日程安排
 *
 */
class SmartCalendar {
    private var treeMap: TreeMap<Int, Int> = TreeMap()

    /**
     * 如果时间添加事件[start,end) 在日历安排中不冲突，则添加，否则不允许添加
     */
    fun book(start: Int, end: Int): Boolean {
        val previous = treeMap.floorKey(start) // 返回的键最大小于等于给定的键
        var next = treeMap.ceilingKey(start)  // 返回的最小键大于等于给定的键
        return if ((previous == null || (treeMap[previous]!! <= start))
            && (next == null || end <= next)
        ) {
            treeMap[start] = end
            true
        } else false
    }
}

fun main() {
    val calender = SmartCalendar()
    println(calender.book(10, 11))
    println(calender.book(11, 14))
    println(calender.book(12, 13))
}