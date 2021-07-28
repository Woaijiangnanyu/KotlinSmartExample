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
 * 获取该节点上一个节点
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
fun middleNodeList(head: ListNode<Int>?): ListNode<Int>? {
    if (head == null) return null
    var fast = head
    var slow = head
    while ((fast?.next != null) and (fast?.next?.next != null)) {
        slow = slow?.next
        fast = fast?.next?.next
    }
    return slow
}

fun mergeTwoNodeList(first: ListNode<Int>?, second: ListNode<Int>?): ListNode<Int>? {
    var one = first
    var two = second
    var temp: ListNode<Int>? = ListNode<Int>(0)
    var lastNext = temp
    while ((one != null) and (two != null)) {
        var a = one?.e ?: Int.MIN_VALUE
        var b = two?.e ?: Int.MIN_VALUE
//        println("a $a -- b $b")
        if (a < b) {
            lastNext?.next = one
            one = one?.next
        } else {
            lastNext?.next = two
            two = two?.next
        }
        lastNext = lastNext?.next
    }
    if (one != null) {
        lastNext?.next = one
    } else {
        lastNext?.next = two
    }
    return temp?.next
}

/***
 * 归并排序方式
 */
fun sortList1(head: ListNode<Int>?): ListNode<Int>? {
    if ((head == null) or (head?.next == null)) return head
    val middle = middleNodeList(head)
    var right = sortList1(middle?.next)
    middle?.next = null
    var left = sortList1(head)
    return mergeTwoNodeList(left, right)
}

/**
 * 快速排序方式
 */
fun sortList(head: ListNode<Int>?): ListNode<Int>? {
    quickSortList(head, null)
    return head
}

fun quickSortList(start: ListNode<Int>?, end: ListNode<Int>?) {
    if (start == end) return  // 这里比较的是对象
    val p = partitionNode(start, end)
    quickSortList(start, p)
    quickSortList(p?.next, end)
}

/**
 * 获取分割点
 */
fun partitionNode(start: ListNode<Int>?, end: ListNode<Int>?): ListNode<Int>? {
    val pivotKey = start?.e // 获取轴值
    var p1 = start
    var p2 = start?.next
    while (p2 != end) {
        if (p2?.e!! < pivotKey!!) {
            p1 = p1?.next
            swapValue(p1, p2)
        }
        p2 = p2?.next
    }
    swapValue(start, p1)
    return p1
}

/**
 * 只做值交换
 */
fun swapValue(p1: ListNode<Int>?, p2: ListNode<Int>?) {
    var temp = p1?.e!!
    p1?.e = p2?.e!!
    p2?.e = temp
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
//    val node1 = ListNode<Int>(1)
//    val node2 = ListNode<Int>(2)
//    val node3 = ListNode<Int>(3)
//    val node4 = ListNode<Int>(4)
//    node1.next = node2
//    node2.next = node3
//    node3.next = node4
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
//    println(middleNodeList(node1)?.e)

//    val node1 = ListNode<Int>(1)
//    val node3 = ListNode<Int>(3)
//    val node5 = ListNode<Int>(5)
//    val node7 = ListNode<Int>(7)
//    node1.next = node3
//    node3.next = node5
//    node5.next = node7
//
//    val node2 = ListNode<Int>(2)
//    val node4 = ListNode<Int>(4)
//    val node6 = ListNode<Int>(6)
//    val node8 = ListNode<Int>(8)
//    node2.next = node4
//    node4.next = node6
//    node6.next = node8

//    printListNode(mergeTwoNodeList(node1, node2))

    val node1 = ListNode<Int>(10)
    val node2 = ListNode<Int>(2)
    val node3 = ListNode<Int>(3)
    val node4 = ListNode<Int>(44)
    node1.next = node2
    node2.next = node3
    node3.next = node4
//    printListNode(sortList1(node1))
    printListNode(sortList(node1))
}
