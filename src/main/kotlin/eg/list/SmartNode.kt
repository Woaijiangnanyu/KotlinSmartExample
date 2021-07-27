package eg.list

/**
 * 在表头插入节点
 */
fun insertToHead(node: ListNode<Int>, value: Int): ListNode<Int> {
    var first = ListNode(value)
    first.next = node
    return first
}

/**
 * 在表头删除节点
 */
fun deleteToHead(node: ListNode<Int>): ListNode<Int>? {
    var fist: ListNode<Int>? = node ?: return null
    fist = node.next
    return fist
}

fun main() {
    val node1 = ListNode<Int>(1)
    val node2 = ListNode<Int>(2)
    val node3 = ListNode<Int>(3)
    val node4 = ListNode<Int>(4)
    node1.next = node2
    node2.next = node3
    node3.next = node4
//    println(insertToHead(node1, 0).e)
    println(deleteToHead(node1)?.e)
}