package app.gkuroda.srapplication.flux.action.search

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.LifecycleSensitiveActionCreator
import app.gkuroda.srapplication.flux.repository.search.SearchRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.kotlin.addTo

class SearchActionCreator(
    private val dispatcher: Dispatcher,
    private val searchRepository: SearchRepository
) : LifecycleSensitiveActionCreator(), SearchActionCreatable {
    override fun getSearchResult(queryString: String) {
        searchRepository.getSearchResult(queryString)
            .subscribeOn(Schedulers.io())
            .map(SearchAction::GetSearchResult)
            .subscribe(dispatcher::dispatch)
            .addTo(disposable)
    }
}