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

fun main() {
    println(wordBreak("AABAA", setOf("AA","AB","A")))
}