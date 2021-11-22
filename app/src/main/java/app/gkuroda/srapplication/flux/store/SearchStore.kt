package app.gkuroda.srapplication.flux.store

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.search.SearchAction
import app.gkuroda.srapplication.flux.api.SearchResponse
import com.github.kittinunf.result.Result
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.kotlin.addTo
import java.lang.Exception

interface SearchStore {
    val searchResult: BehaviorRelay<Result<SearchResponse, Exception>>
}

fun Store.subscribeSearchStore(dispatcher: Dispatcher) {
    dispatcher.onImpl<SearchAction.GetSearchResult>()
        .map { it.result }
        .subscribe(searchResult::accept)
        .addTo(disposable)
}