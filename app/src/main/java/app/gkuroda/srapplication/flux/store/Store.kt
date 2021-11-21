package app.gkuroda.srapplication.flux.store

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.Action
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class Store(private val dispatcher: Dispatcher) : StoreInterface {

    val reducerThread: Scheduler = Schedulers.computation()

    val disposable = CompositeDisposable()

    override val searchResult: BehaviorRelay<String> = BehaviorRelay.create()

    init {
        subscribe()
    }

    private fun subscribe() {
        subscribeSearchStore(dispatcher)
    }

    inline fun <reified T : Action> Dispatcher.onImpl(): Flowable<T> =
        on<T>().observeOn(reducerThread)

}