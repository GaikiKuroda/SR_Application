package app.gkuroda.srapplication.flux.action.search

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.LifecycleSensitiveActionCreator
import app.gkuroda.srapplication.flux.repository.search.SearchRepository
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchActionCreator(
    private val dispatcher: Dispatcher,
    private val searchRepository: SearchRepository
) : LifecycleSensitiveActionCreator(), SearchActionCreatable {
    override fun getSampleIntItem(item: Int) {
        searchRepository.getSampleIntItem(item)
            .subscribeOn(Schedulers.io())
            .map(SearchAction::GetSampleIntItem)
            .subscribe(dispatcher::dispatch)
            .addTo(disposable)
    }
}