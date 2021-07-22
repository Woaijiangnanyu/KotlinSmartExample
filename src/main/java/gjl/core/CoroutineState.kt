package gjl.core

sealed class CoroutineState {

    private var disableList: DisposableList = DisposableList.Nil


    /**
     * from 调用之前一定会先创建CoroutineState类
     * 防止多线程访问可变资源产生异常情况，我们采用重新创建新的状态来完成状态流转
     */
    fun from(state: CoroutineState): CoroutineState {
        this.disableList = state.disableList
        return this
    }

    /**
     * 添加
     * 将带有中断函数的变量传递给新的状态集
     */
    fun with(disposable: Disposable): CoroutineState {
        this.disableList = DisposableList.Cons(disposable, this.disableList)
        return this
    }

    /**
     * 移除
     */
    fun withOut(disposable: Disposable): CoroutineState {
        this.disableList = this.disableList.remove(disposable)
        return this
    }

    fun <T> notifyCompletion(result: Result<T>) {
        this.disableList.loopOn<CompletionHandlerDisposable<T>> {
            it.onComplete(result)
        }
    }

    fun clear() {
        this.disableList = DisposableList.Nil
    }

    class InComplete : CoroutineState()
    class Complete<T>(val value: T? = null, val exception: Throwable? = null):CoroutineState()
}