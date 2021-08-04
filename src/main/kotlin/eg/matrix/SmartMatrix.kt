package eg.matrix

fun spiralOrder(src: Array<Array<Int>>): MutableList<Int>? {
    if (src.isEmpty()) return null
    val arry = mutableListOf<Int>()
    var up = 0
    var left = 0
    var right = src[0].size - 1
    var down = src.size - 1
    var x = 0
    var y = 0
    while ((up <= down) && (left <= right)) {
        y = left
        while ((y <= right) && boundValid(left, up, right, down)) {
            arry.add(src[x][y])
            y++
        }
        y--
        up++
        x = up
        while ((x <= down) && boundValid(left, up, right, down)) {
            arry.add(src[x][y])
            x++
        }
        x--
        right--
        y = right
        while ((y >= left) && boundValid(left, up, right, down)) {
            arry.add(src[x][y])
            y--
        }
        y++
        down--
        x = down
        while ((x >= up) && boundValid(left, up, right, down)) {
            arry.add(src[x][y])
            x--
        }
        x++
        left++
    }
    return arry
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