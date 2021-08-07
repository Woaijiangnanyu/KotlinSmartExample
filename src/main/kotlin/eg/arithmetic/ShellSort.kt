package eg.arithmetic

/**
 * 希尔排序
 * （分组插入排序）
 * 关键是确定步长 gap
 */
fun shellSort(A: Array<Int>): Array<Int> {
    var temp = 0
    var gap = 1
    while (gap < A.size / 3) {
        gap = gap * 3 + 1
    }
    while (gap>0){
        for (i in gap until A.size){
            temp = A[i]
            var j = i - gap
            while ((j>=0) && (A[j]>temp)){
                A[j+gap] = A[j]
                j-=gap
            }
            A[j+gap] = temp
        }
        gap/=3
    }
    return A
}

fun main() {
    shellSort(arrayOf(9, 1, 2, 5, 7, 4, 8, 6, 3, 5)).forEach(::println)
}