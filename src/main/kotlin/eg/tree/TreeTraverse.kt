package eg.tree

import java.util.*

/**
 * 先序遍历
 *
 * */

fun preTravers(value: TreeNode?) {
    if (value != null) {
        print(value.value)
        preTravers(value.left)
        preTravers(value.right)
    }
}


/***
 * 中序遍历
 */

fun inTraverse(value: TreeNode?) {
    if (value != null) {
        inTraverse(value.left)
        print(value.value)
        inTraverse(value.right)
    }
}

/***
 * 后序遍历
 */

fun postTraverse(value: TreeNode?) {
    if (value != null) {
        postTraverse(value.left)
        postTraverse(value.right)
        print(value.value)
    }
}

/***
 * 层次遍历 (DFS)
 */
fun levelOrder(treeNode: TreeNode?): MutableList<MutableList<String>> {
    var result = mutableListOf<MutableList<String>>()
    if (treeNode == null) return result
    dfs(treeNode, result, 0)
    return result
}


/**
 * Z型遍历
 */
fun zLevelOrder(treeNode: TreeNode?): MutableList<MutableList<String>> {
    val res = mutableListOf<MutableList<String>>()
    if (treeNode == null) return res
    var queue = LinkedList<TreeNode>()
    queue.offer(treeNode)
    var isLeft = false
    while (!queue.isEmpty()) {
        var list = mutableListOf<String>()
        val size = queue.size
        isLeft = !isLeft
        for (i in 0 until size) {
            val value = if (isLeft) {
                queue.pollFirst()
            } else queue.pollLast()
            list.add(value.value)
            if (isLeft) {
                if (value.left != null)
                    queue.offerLast(value.left)

                if (value.right != null)
                    queue.offerLast(value.right)


            } else {
                if (value.right != null)
                    queue.offerFirst(value.right)

                if (value.left != null)
                    queue.offerFirst(value.left)

            }
        }
        res.add(list)
    }

    return res
}


fun dfs(treeNode: TreeNode?, res: MutableList<MutableList<String>>, level: Int) {
    if (treeNode == null) return
    if (level == res.size) {
        res.add(mutableListOf())
    }
    res.get(level).add(treeNode.value)
    dfs(treeNode.left, res, level + 1)
    dfs(treeNode.right, res, level + 1)
}

/***
 * 层次遍历（BFS）
 */

fun levelOrder2(treeNode: TreeNode?): MutableList<MutableList<String>> {
    var result = mutableListOf<MutableList<String>>()
    if (treeNode == null) return result
    var queue = LinkedList<TreeNode>()
    queue.offer(treeNode)
    while (!queue.isEmpty()) {
        var level = mutableListOf<String>()
        var size = level.size
        for (i in 0..size) {
            var value = queue.poll()
            level.add(value.value)
            if (value.left != null) queue.offer(value.left)
            if (value.right != null) queue.offer(value.right)
        }
        result.add(level)
    }

    return result
}

fun main() {
    var a = TreeNode("A")
    var b = TreeNode("B")
    var c = TreeNode("C")
    var d = TreeNode("D")
    var e = TreeNode("E")
    var f = TreeNode("F")
    var g = TreeNode("G")
    var h = TreeNode("H")
    var i = TreeNode("I")
    var j = TreeNode("J")

    a.left = b
    a.right = c
    b.left = d
    b.right = e
    d.left = h
    d.right = i
    e.left = j
    c.left = f
    c.right = g
//    preTravers(a)

//    inTraverse(a)

//    postTraverse(a)

//    levelOrder(a).forEach {
//        for (value in it) {
//            print(value)
//        }
//    }

//    levelOrder2(a).forEach { for (value in it) print(value) }

    //ACBDEFGJIH
    zLevelOrder(a).forEach { for (value in it) print(value) }
}