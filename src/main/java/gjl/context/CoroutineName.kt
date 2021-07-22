package gjl.context

import gjl.Job
import kotlin.coroutines.CoroutineContext

class CoroutineName(val name:String):CoroutineContext.Element {
    companion object kEY:CoroutineContext.Key<CoroutineName>
    override val key = Job.Key
    override fun toString(): String {
        return name
    }
}