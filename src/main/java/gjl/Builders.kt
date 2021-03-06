package gjl

import gjl.context.CoroutineName
import gjl.core.StandardCoroutine
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
private val coroutineIndex = AtomicInteger(0)
fun launch(context:CoroutineContext = EmptyCoroutineContext,block:suspend ()->Unit):Job{
    val completion = StandardCoroutine(newCoroutineContext(context))
    block.startCoroutine(completion)
    return completion
}

fun newCoroutineContext(context: CoroutineContext):CoroutineContext{
    val combined = context + CoroutineName("@coroutine${coroutineIndex.getAndIncrement()}")
    return combined
}