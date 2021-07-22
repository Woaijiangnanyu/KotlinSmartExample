package eg

import java.util.concurrent.ConcurrentHashMap
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KClass
import kotlin.reflect.KProperty

abstract class AbsModel01 {
    init {
        Models01.run {
            this@AbsModel01.register() //代指子类
        }
    }
}

object Models01 {
    private val modelMap = ConcurrentHashMap<Class<out AbsModel01>, AbsModel01>()

    fun <T : AbsModel01> KClass<T>.get(): T {
        return modelMap[this.java] as T
    }

    fun AbsModel01.register() {
        modelMap[this.javaClass] = this
    }
}

class DatabaseModel01 : AbsModel01() {
    fun query(sql: String): Int = 0
}

class NetWorkModel01 : AbsModel01() {
    fun get(url: String): String = """{"code":0}"""
}

class ModelDelegate01<T : AbsModel01>(val kClass: KClass<T>) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return Models01.run {
            kClass.get()
        }
    }
}

/**
 * 内联特化
 */
inline fun <reified T : AbsModel01> modeOf(): ModelDelegate01<T> {
    return ModelDelegate01(T::class)
}

class MainViewModel01 {
    val databaseModel01 by modeOf<DatabaseModel01>()
    val netWorkModel01 by modeOf<NetWorkModel01>()
}

fun initModels01(){
    DatabaseModel01()
    NetWorkModel01()
}

fun main() {
    initModels01()
    var mainViewModel = MainViewModel01()
    println(mainViewModel.databaseModel01.query("hahahhaha"))
    println(mainViewModel.netWorkModel01.get("http:www.kengni.com"))
}