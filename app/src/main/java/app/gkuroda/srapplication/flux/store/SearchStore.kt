package app.gkuroda.srapplication.flux.store

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.search.SearchAction
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.kotlin.addTo

interface SearchStore {
    val searchResult: BehaviorRelay<String>
}

fun Store.subscribeSearchStore(dispatcher: Dispatcher) {
    dispatcher.onImpl<SearchAction.GetSearchResult>()
        .map { it.result }
        .subscribe(searchResult::accept)
        .addTo(disposable)
}