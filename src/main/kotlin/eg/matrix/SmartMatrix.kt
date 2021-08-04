package eg.matrix

fun spiralOrder(src: Array<Array<Int>>): MutableList<Int>? {
    if (src.isEmpty()) return null
    val airy = mutableListOf<Int>()
    var up = 0 // 上边界
    var left = 0 // 左边界
    var right = src[0].size - 1 //右边界
    var down = src.size - 1 //下边界
    var x = 0  // 初始坐标
    var y: Int // 初始坐标
    while ((up <= down) && (left <= right)) {
        y = left
        //向右移动
        while ((y <= right) && boundValid(left, up, right, down)) {
            airy.add(src[x][y])
            y++
        }
        y--
        up++
        x = up
        //向下移动
        while ((x <= down) && boundValid(left, up, right, down)) {
            airy.add(src[x][y])
            x++
        }
        x--
        right--
        y = right
        //向左移动
        while ((y >= left) && boundValid(left, up, right, down)) {
            airy.add(src[x][y])
            y--
        }
        y++
        down--
        x = down
        //向上移动
        while ((x >= up) && boundValid(left, up, right, down)) {
            airy.add(src[x][y])
            x--
        }
        x++
        left++
    }
    return airy
}

/**
 * 检查是否边界碰壁
 */
fun boundValid(left: Int, up: Int, right: Int, down: Int): Boolean {
    return (up <= down) && (left <= right)
}

fun main() {
    var aa = arrayOfNulls<Array<Int>>(3)
    aa[0] = arrayOf(1, 2, 3, 4)
    aa[1] = arrayOf(5, 6, 7, 8)
    aa[2] = arrayOf(9, 10, 11, 12)
    aa as Array<Array<Int>>
    spiralOrder(aa)?.forEach(::println)
}