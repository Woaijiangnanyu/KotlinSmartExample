package gjl.core

import gjl.Job

typealias OnCompleteT<T> = (Result<T>) -> Unit  // 函数类型 （Result<T>）-> Unit

interface Disposable {  // 移除挂起函数
    fun dispose()
}

class CompletionHandlerDisposable<T>(val job: Job, val onComplete: OnCompleteT<T>) : Disposable {
    override fun dispose() {
        job.remove(this)
    }
}

//递归
sealed class DisposableList {
    object Nil : DisposableList()
    class Cons(val head: Disposable, val tail: DisposableList) : DisposableList()
}

fun DisposableList.remove(disposable: Disposable): DisposableList {
    return when (this) {
        is DisposableList.Nil -> this
        is DisposableList.Cons -> {
            if (disposable == head) {
                return tail
            } else {
                DisposableList.Cons(head, tail.remove(disposable))
            }
        }
    }
}

// 尾递归
tailrec fun DisposableList.forEach(action: (Disposable) -> Unit): Unit = when (this) {
    DisposableList.Nil -> Unit
    is DisposableList.Cons -> {
        action(head)
        this.tail.forEach(action)
    }
}

/**
 * inline reified 内联特化     crossinline 取消内联特化
 */
inline fun <reified T : Disposable> DisposableList.loopOn(crossinline action: (T) -> Unit) = forEach {
    when (it) {
        is T -> action(it)
    }
}