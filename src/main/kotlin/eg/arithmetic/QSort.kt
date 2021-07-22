package eg.arithmetic

fun QSort(values: IntArray, start: Int, end: Int): IntArray {
    val piont = values[0]
    var i = start
    var j = end
    while (i < j) {
        while ((i < j) and (values[j] > piont)) {
            j--
        }
        while ((i < j) and (values[i] < piont)) {
            i++
        }
        if ((values[i] == values[j]) && (i < j)) {
            i++ // 防止空转
        } else {
            val temp = values[i]
            values[i] = values[j]
            values[j] = temp
        }
    }
    if (i - 1 > start) QSort(values, start, i - 1)
    if (j + 1 < end) QSort(values, j + 1, end)
    return values
}

fun main() {
    val arr = intArrayOf(3,3,3,7,9,122344,4656,34,34,4656,5,6,7,8,9,343,57765,23,12321)
    QSort(arr,0,arr.size-1).forEach(::println)
}