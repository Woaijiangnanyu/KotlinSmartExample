package eg.gray

import gjl.utils.SmartSort

/**
 * 雷格编码
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个二进制的差异。给定一个非负整数 n ，表示该代码中所有二进制的总数，请找出其格雷编码顺序。一个格雷编码顺序必须以 0 开始，并覆盖所有的 2n 个整数。
 * 例子——输入：2；输出：[0, 1, 3, 2]；解释: 0 - 0000，1 - 0001，3 - 0011，2 - 0010
 * ^ ：取反
 * 格雷码生成公式：G(i) = i ^ (i >> 2)
 */
fun grayCode(n: Int): MutableList<Int> {
    val gray = mutableListOf<Int>()
    for (i in 0 until (n.shl(1))){ // shl 左移运算符
        gray.add(i.xor(i.shr(1))) // xor 按位异或 shr右移运算符
    }
    return gray
}

fun main() {
//    var gray = SmartSort()
//    gray.grayCode(2).forEach(::println)
    grayCode(2).forEach(::println)
}