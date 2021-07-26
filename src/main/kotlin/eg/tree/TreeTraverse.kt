package eg.tree

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

fun inTraverse(value: TreeNode?){
    if (value != null){
        inTraverse(value.left)
        print(value.value)
        inTraverse(value.right)
    }
}

/***
 * 后序遍历
 */

fun postTraverse(value: TreeNode?){
    if (value != null){
        postTraverse(value.left)
        postTraverse(value.right)
        print(value.value)
    }
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
    e.left= j
    c.left = f
    c.right = g
//    preTravers(a)

//    inTraverse(a)

    postTraverse(a)
}