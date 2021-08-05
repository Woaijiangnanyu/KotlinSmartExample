package eg.matrix

/**
 * 螺旋矩阵
 */
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

/**
 * 判断数独是否合法
 */
fun isValidSudoku(board: Array<Array<String>>): Boolean {
    var screen = HashSet<String>()
    for (i in board.indices) {
        for (j in board[0].indices) {
            var number = board[i][j]
            if (number != ".") {
                if (!screen.add("$number in this row $i")
                    || !screen.add("$number in this col $j")
                    || !screen.add("$number in this box ${i / 3} - ${j / 3}")
                ) return false
            }
        }
    }
    return true
}

fun main() {
//    val aa = arrayOfNulls<Array<Int>>(3)
//    aa[0] = arrayOf(1, 2, 3, 4)
//    aa[1] = arrayOf(5, 6, 7, 8)
//    aa[2] = arrayOf(9, 10, 11, 12)
//    aa as Array<Array<Int>>
//    spiralOrder(aa)?.forEach(::println)
    val aa = arrayOfNulls<Array<String>>(9)
    aa[0] = arrayOf(".", ".", "8", ".",".",".","2",".",".")
    aa[1] = arrayOf(".", "3", ".", "8",".","2",".","6",".")
    aa[2] = arrayOf("7", ".", ".", ".","9",".",".",".","5")
    aa[3] = arrayOf(".", "5", ".", ".",".",".",".","1",".")
    aa[4] = arrayOf(".", ".", "4", ".",".",".","6",".",".")
    aa[5] = arrayOf(".", "2", ".", ".",".",".",".","7",".")
    aa[6] = arrayOf("4", ".", ".", ".","8",".",".",".","6")
    aa[7] = arrayOf(".", "7", ".", "1",".","3",".","9",".")
    aa[8] = arrayOf(".", ".", "1", ".",".",".","8",".",".")
    aa as Array<Array<String>>
    println(isValidSudoku(aa))
}