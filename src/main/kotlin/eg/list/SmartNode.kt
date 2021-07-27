package eg.list

/**
 * 在表头插入节点
 */
fun insertToHead(node: ListNode<Int>, value: Int): ListNode<Int> {
    val first = ListNode(value)
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
    return if (tail == null) {
        ListNode(value)
    } else {
        tail.next = ListNode(value)
        tail.next
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
fun deleteOtherNode(head: ListNode<Int>?, delete: ListNode<Int>?) {
    val pre = getPreNode(head, delete)
    pre?.next = pre?.next?.next
}

/***
 * 链表翻转
 */
fun reverseNodeList(head: ListNode<Int>?): ListNode<Int>? {
    var pre: ListNode<Int>? = null
    var current = head
    var next = head
    while (next != null) {
        next = current?.next
        current?.next = pre
        pre = current
        current = next
    }
    return pre // 原始的最后一个元素指向->null
}

/**
 * 判断是否含有环形结构
 */
fun hasCycle(head: ListNode<Int>?): Boolean {
    if ((head == null) or (head?.next == null)) return false
    var fast = head
    var slow = head
    while ((fast?.next != null) and (fast?.next?.next != null)) {
        slow = slow?.next
        fast = fast?.next?.next
        if (slow == fast) {
            return true
        }
    }
    return false
}

/**
 * 链表中间值
 */
fun middleNodeList(head: ListNode<Int>?):ListNode<Int>?{
    if (head == null) return null
    var fast = head
    var slow = head
    while ((fast?.next != null) and (fast?.next?.next != null)){
        slow = slow?.next
        fast = fast?.next?.next
    }
    return slow
}
/**
 * 链表打印
 */
fun printListNode(head: ListNode<Int>?) {
    var index = head
    while (index != null) {
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
//    deleteOtherNode(node1,node2)
//    printListNode(node1)
//    printListNode(reverseNodeList(node1))

//    val node1 = ListNode<Int>(1)
//    val node2 = ListNode<Int>(2)
//    val node3 = ListNode<Int>(3)
//    val node4 = ListNode<Int>(4)
//    node1.next = node2
//    node2.next = node3
//    node3.next = node4
//    node4.next = node1
//    println(hasCycle(node1))
    println(middleNodeList(node1)?.e)
}
