package gjl.sample

import gjl.launch
import log
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun main(){
    val job = launch{
        log(1)
        val result = hello()
        log(2,result)
        log(4)
    }
    log(job.isActive)
    job.join()
    log(3)
}

suspend fun hello() = suspendCoroutine<Int> {
    thread(isDaemon = true) { Thread.sleep(1000)
        it.resume(10086)
    }
}