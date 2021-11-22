package app.gkuroda.srapplication.flux.action.search

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.LifecycleSensitiveActionCreator
import app.gkuroda.srapplication.flux.api.SearchResponse
import app.gkuroda.srapplication.flux.repository.search.SearchRepository
import com.github.kittinunf.result.Result
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class SearchActionCreator(
    private val dispatcher: Dispatcher,
    private val searchRepository: SearchRepository
) : LifecycleSensitiveActionCreator(), SearchActionCreatable {
    override fun getSearchResult(queryString: String) {
        searchRepository.getSearchResult(queryString)
            .subscribeOn(Schedulers.io())
            .map<Result<SearchResponse, Exception>> {
                Result.success(it)
            }
            .onErrorReturn {
                Result.failure(it as Exception)
            }
            .map(SearchAction::GetSearchResult)
            .subscribe(dispatcher::dispatch)
            .addTo(disposable)
    }
}