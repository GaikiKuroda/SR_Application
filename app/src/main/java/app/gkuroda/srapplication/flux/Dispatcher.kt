package app.gkuroda.srapplication.flux

import app.gkuroda.srapplication.flux.action.Action
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.FlowableProcessor
import io.reactivex.rxjava3.processors.PublishProcessor

class Dispatcher {
    val flowableProcessor: FlowableProcessor<Action> = PublishProcessor.create()

    fun dispatch(action: Action) {
        flowableProcessor.onNext(action)
    }

    inline fun <reified T : Action> on(): Flowable<T> = flowableProcessor.filter { it is T }.map { it as T }
}