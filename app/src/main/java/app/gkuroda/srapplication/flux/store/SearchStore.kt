package app.gkuroda.srapplication.flux.store

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.search.SearchAction
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.kotlin.addTo

interface SearchStore {
    val sampleIntItem: BehaviorRelay<Int>
}

fun Store.subscribeSearchStore(dispatcher: Dispatcher) {
    dispatcher.onImpl<SearchAction.GetSampleIntItem>()
        .map { it.result }
        .subscribe(sampleIntItem::accept)
        .addTo(disposable)
}