package eg

import kotlin.properties.Delegates

class StateManager {
    var state:Int by Delegates.observable(0){
        property, oldValue, newValue ->
        println("State changed from $oldValue -> $newValue")
    }
}

fun main() {
    val stateManager= StateManager()
    stateManager.state =3
    stateManager.state = 4

}