package eg

import java.util.concurrent.ConcurrentHashMap
import kotlin.reflect.KProperty

abstract class AbsModel {
    init {
        Models.run {
            this@AbsModel.register()
        }
    }
}

object Models {
    private val modelMap = ConcurrentHashMap<String, AbsModel>()
    fun AbsModel.register(name: String = this.javaClass.simpleName) {
        modelMap[name] = this;
    }

    fun <T : AbsModel> String.get(): T {
        return modelMap[this] as T
    }
}

class DataModel : AbsModel() {
    fun query(sql: String): String = "0"
}

class NetWorkModel : AbsModel() {
    fun get(url: String): String = """{"code":0}"""
}


object ModelDelegate {
    operator fun <T : AbsModel> getValue(self: Any, property: KProperty<*>): T {
        return Models.run {
            property.name.capitalize().get()
        }

    }
}

fun initModels(){
    NetWorkModel()
    DataModel()
}

class MainModel{
    val dataModel:DataModel by ModelDelegate
    val netWorkModel:NetWorkModel by ModelDelegate
}

fun main() {
    initModels()
    var mainModel = MainModel()
    println(mainModel.dataModel.query("hahha"))
    println(mainModel.netWorkModel.get("http"))
}