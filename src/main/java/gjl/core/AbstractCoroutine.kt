package gjl.core

import gjl.Job
import gjl.OnComplete
import log
import java.lang.IllegalStateException
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

abstract class AbstractCoroutine<T>(override val context: CoroutineContext) : Job, Continuation<T> {

    protected val state = AtomicReference<CoroutineState>()

    init {
        state.set(CoroutineState.InComplete())
    }

    override val key: CoroutineContext.Key<*>
        get() = super.key
    override val isActive: Boolean
        get() = state.get() is CoroutineState.InComplete

    override fun invokeOnCompletion(onComplete: OnComplete): Disposable {
        return doOnCompleted {
            onComplete()
        }
    }

    /**
     * 挂起等待恢复
     */
    override suspend fun join() {
        when (state.get()) {
            is CoroutineState.InComplete -> return joinSuspend()
            is CoroutineState.Complete<*> -> return
        }
    }

    /**
     * 真正的挂起函数
     */
    private suspend fun joinSuspend() = suspendCoroutine<Unit> { continuation ->
        doOnCompleted { result ->
            continuation.resume(Unit)
        }
    }


    private fun doOnCompleted(block: (Result<T>) -> Unit): Disposable {
        val disposable = CompletionHandlerDisposable(this, block)
        val newState = state.updateAndGet { prev ->
            log("doOnCompleted - 状态流转...")
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.InComplete().from(prev).with(disposable)
                }
                is CoroutineState.Complete<*> -> {
                    prev
                }
            }
        }

        (newState as? CoroutineState.Complete<T>)?.let {
            block(
                when {
                    it.value != null -> Result.success(it.value)
                    it.exception != null -> Result.failure(it.exception)
                    else -> throw IllegalStateException("Won't happen!")
                }
            )
        }

        return disposable
    }

    override fun remove(disposable: Disposable) {
        state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.InComplete -> {
                    CoroutineState.InComplete().from(prev).withOut(disposable)
                }
                is CoroutineState.Complete<*> -> prev
            }
        }
    }

    override fun resumeWith(result: Result<T>) {
        val newState = state.updateAndGet { pre ->
            log("resumeWith - 状态流转...")
            when (pre) {
                is CoroutineState.InComplete -> {
                    CoroutineState.Complete(result.getOrNull(), result.exceptionOrNull()).from(pre)
                }
                is CoroutineState.Complete<*> -> {
                    throw IllegalStateException("Already completed")
                }
            }
        }

//        (newState as CoroutineState.Complete<T>).exception?.let {  }
        newState.notifyCompletion(result)
        newState.clear()
    }
}