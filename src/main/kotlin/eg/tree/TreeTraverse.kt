package eg.tree

import java.util.*
import kotlin.math.min

/**
 * 先序遍历
 *
 * */

fun preTravers(value: TreeNode<String>?) {
    if (value != null) {
        print(value.value)
        preTravers(value.left)
        preTravers(value.right)
    }
}


/***
 * 中序遍历
 */

fun inTraverse(value: TreeNode<String>?) {
    if (value != null) {
        inTraverse(value.left)
        print(value.value)
        inTraverse(value.right)
    }
}

/***
 * 后序遍历
 */

fun postTraverse(value: TreeNode<String>?) {
    if (value != null) {
        postTraverse(value.left)
        postTraverse(value.right)
        print(value.value)
    }
}

/***
 * 层次遍历 (DFS)
 */
fun levelOrder(treeNode: TreeNode<String>?): MutableList<MutableList<String>> {
    var result = mutableListOf<MutableList<String>>()
    if (treeNode == null) return result
    dfs(treeNode, result, 0)
    return result
}


/**
 * Z型遍历
 */
fun zLevelOrder(treeNode: TreeNode<String>?): MutableList<MutableList<String>> {
    val res = mutableListOf<MutableList<String>>()
    if (treeNode == null) return res
    var queue = LinkedList<TreeNode<String>>()
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


fun dfs(treeNode: TreeNode<String>?, res: MutableList<MutableList<String>>, level: Int) {
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

fun levelOrder2(treeNode: TreeNode<String>?): MutableList<MutableList<String>> {
    var result = mutableListOf<MutableList<String>>()
    if (treeNode == null) return result
    var queue = LinkedList<TreeNode<String>>()
    queue.offer(treeNode)
    while (!queue.isEmpty()) {
        var level = mutableListOf<String>()
        var size = queue.size
        for (i in 0 until size) {
            var value = queue.poll()
            level.add(value.value)
            if (value.left != null) queue.offer(value.left)
            if (value.right != null) queue.offer(value.right)
        }
        result.add(level)
    }

    return result
}

/**
 * 左右翻转
 */
fun invert(treeNode: TreeNode<String>?): TreeNode<String>? {
    if (treeNode != null) {

        var tempNode = treeNode.left
        treeNode.left = treeNode.right
        treeNode.right = tempNode

        invert(treeNode.left)
        invert(treeNode.right)
    }
    return treeNode
}

/**
 * 获取最大值
 */
//fun getMax(treeNode: TreeNode?):Int{
//    if (treeNode == null){
//        return Int.MIN_VALUE
//    }
//    var left = getMax(treeNode.left)
//    var right = getMax(treeNode.right)
//    return Math.max(Math.max(left,right),treeNode.value)
//}


/**
 * 最大深度
 */
fun maxDepth(treeNode: TreeNode<String>?): Int {
    if (treeNode == null) return 0
    val left = maxDepth(treeNode.left)
    val right = maxDepth(treeNode.right)
    return Math.max(left, right) + 1
}

/**
 * 最小深度
 */

fun minDepth(treeNode: TreeNode<String>?): Int {
    if (treeNode == null) return 0
    val left = minDepth(treeNode.left)
    val right = minDepth(treeNode.right)
    if (left == 0) {
        return right + 1
    } else if (right == 0) {
        return left + 1
    } else {
        return Math.min(left, right) + 1
    }
}


/**
 * 是否是平衡二叉树
 */
fun isBalanced(treeNode: TreeNode<String>?): Boolean {
    return mexDepth(treeNode) != -1
}

fun mexDepth(treeNode: TreeNode<String>?): Int {
    if (treeNode == null) {
        return 0
    }

    val left = mexDepth(treeNode.left)
    val right = mexDepth(treeNode.right)

    if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
        return -1;
    }
    return Math.max(left, right) + 1
}

/**
 * 二叉查找树（BST）
 * 特点：
 * 一棵空树 或
 * @sample 若任意节点的左子树不为空，则左子树上所有节点的值都要比根节点值都要小
 * @sample 若任意节点的右子树不为空，则右子树上所有节点的值都要比根节点值要大
 * @sample 任意节点的左右子树同样为二叉查找树
 * @sample 不存在两个键值相等的节点
 */
fun isValidBST(root: TreeNode<Long>): Boolean {
    return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE)
}

fun isValidBST(root: TreeNode<Long>?, min: Long, max: Long): Boolean {
    if (root == null) return true
    println("root.value:${root.value}")
    println("min$min")
    println("max$max")
    if ((root.value <= min) or (root.value >= max)) return false
    return (isValidBST(root.left, min, root.value)
            and
            isValidBST(root.right, root.value, max))

}

fun main() {
//    var a = TreeNode("A")
//    var b = TreeNode("B")
//    var c = TreeNode("C")
//    var d = TreeNode("D")
//    var e = TreeNode("E")
//    var f = TreeNode("F")
//    var g = TreeNode("G")
//    var h = TreeNode("H")
//    var i = TreeNode("I")
//    var j = TreeNode("J")
//
//    a.left = b
//    a.right = c
//    b.left = d
//    b.right = e
//    d.left = h
//    d.right = i
//    e.left = j
//    c.left = f
//    c.right = g
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
//    zLevelOrder(a).forEach { for (value in it) print(value) }
//    levelOrder(invert(a)).forEach {
//        for (value in it) {
//            print(value)
//        }
//    }
//    println(maxDepth(a))
//    println(minDepth(a))
//    println(isBalanced(a))

    var tree1 = TreeNode<Long>(1)
    var tree2 = TreeNode<Long>(5)
    var tree3 = TreeNode<Long>(8)
    var tree4 = TreeNode<Long>(2)
    var tree5 = TreeNode<Long>(3)
    tree1.left= tree2
    tree1.right = tree3
    tree2.left = tree4
    tree2.right = tree5
    println(isValidBST(tree1))
}