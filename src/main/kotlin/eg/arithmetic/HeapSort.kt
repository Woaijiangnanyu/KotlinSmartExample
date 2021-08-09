package eg.arithmetic

/**
 * 堆排序
 */
fun heapSort(A: Array<Int>): Array<Int> {
    var len = A.size - 1
    var endIndex = (len - 1).shr(1)

    //第一步将数组堆化
    //endIndex 是第一个非叶子节点（从第一个非叶子节点开始，无需从最后一个叶子节点开始）
    // 叶子节点可以看作已经符合堆要求的节点，根节点就是他自己且以下值最大
    for (i in endIndex downTo 0) {
        maxHeap(i, len, A)
    }
    //第二步
    //对堆化数据进行排序
    //每次都是移出最顶端根节点A[0],与最尾部节点位置调换，同时遍历长度 len-1
    //直至末排序的堆len = 0
    for (i in len downTo 0) {
        swap(0, i, A)
        maxHeap(0, i - 1, A)
    }
    return A
}

fun swap(i: Int, j: Int, A: Array<Int>) {
    val temp = A[i]
    A[i] = A[j]
    A[j] = temp
}

/**
 * 调整索引为 index 处的数据，使其符合堆的特性
 * @param index 需要堆化处理的数据的索引
 * @param len 未排序的堆（数组）的长度
 */
fun maxHeap(index: Int, len: Int, A: Array<Int>) {
    val nodeLeft = index.shl(1) + 1 //左子节点suoyin
    val nodeRight = nodeLeft + 1//右子节点索引
    var maxIndex = nodeLeft//子节点最大值索引（默认是左子节点索引）
    if (nodeLeft > len) return //超出索引范围返回
    if (nodeRight <= len && A[nodeLeft] < A[nodeRight]) {//右子节点值大于左子节点值，记录最大值索引为右节点索引值
        maxIndex = nodeRight
    }
    if (A[index] < A[maxIndex]) {//子节点值大于根节点值，进行交换，并对子节点的子节点进行判断
        swap(index, maxIndex, A)
        maxHeap(maxIndex, len, A)
    }
}

fun main() {
    heapSort(arrayOf(3, 5, 3, 0, 8, 6, 1, 5, 8, 6, 2, 4, 9, 4, 7, 0, 1, 8, 9, 7, 3, 1, 2, 5, 9, 7, 4, 0, 2, 6)).forEach(
        ::println
    )
}