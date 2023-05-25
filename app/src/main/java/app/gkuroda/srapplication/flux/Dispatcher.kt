package app.gkuroda.srapplication.flux

import app.gkuroda.srapplication.flux.action.Action
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map

class Dispatcher {
    val flowableProcessor: MutableSharedFlow<Action> = MutableSharedFlow<Action>(3, 3)

    fun dispatch(action: Action) {
        flowableProcessor.tryEmit(action)
    }

    inline fun <reified T : Action> on(): Flow<T> =
        flowableProcessor.filter { it is T }.map { it as T }
}