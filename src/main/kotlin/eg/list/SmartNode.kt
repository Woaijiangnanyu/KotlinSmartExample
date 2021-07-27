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

/***
 * 获取尾节点
 */
fun getTailNode(head: ListNode<Int>?): ListNode<Int>? {
    var tail = head
    while (tail?.next != null) {
        tail = tail.next
    }
    return tail
}

/**
 * 插入尾节点
 */
fun insertTailNode(head: ListNode<Int>?, value: Int): ListNode<Int>? {
    val tail = getTailNode(head)
    if (tail == null) {
        return ListNode(value)
    } else {
        tail.next = ListNode(value)
        return tail.next
    }
}

/**
 * 获取该节点上个节点
 */
fun getPreNode(head: ListNode<Int>?, delete: ListNode<Int>?): ListNode<Int>? {
    var pre = head
    while (pre?.next != delete) {
        pre = pre?.next
    }
    return pre
}

/**
 * 删除任意节点
 */
fun deleteOtherNode(head: ListNode<Int>?, delete: ListNode<Int>?){
    var pre = getPreNode(head,delete)
    pre?.next = pre?.next?.next
}

fun printListNode(head: ListNode<Int>?){
    var index = head
    while (index != null){
        println(index.e)
        index = index.next
    }
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
//    println(deleteToHead(node1)?.e)
//    println(insertTailNode(node1,5)?.e)
    deleteOtherNode(node1,node2)
    printListNode(node1)
}