package gjl

import gjl.core.Disposable
import kotlin.coroutines.CoroutineContext

typealias OnComplete = () -> Unit


interface Job :CoroutineContext.Element{

    companion object Key:CoroutineContext.Key<Job>

    override val key:CoroutineContext.Key<*> get() = Job

    val isActive:Boolean

    fun invokeOnCompletion(onComplete: OnComplete): Disposable

    suspend fun join()

    fun remove(disposable: Disposable)
}