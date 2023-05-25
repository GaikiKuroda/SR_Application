package app.gkuroda.srapplication.flux.action.search

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.IOScopeSensitiveActionCreator
import app.gkuroda.srapplication.flux.repository.search.SearchRepository
import com.github.kittinunf.result.Result
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchActionCreator(
    private val dispatcher: Dispatcher,
    private val searchRepository: SearchRepository
) : IOScopeSensitiveActionCreator(), SearchActionCreatable {
    override fun getSearchResult(queryString: String) {
        scope.launch {
            searchRepository.getSearchResult(queryString)
                .map {
                    Result.success(it)
                }
                .catch {
                    Result.failure(it as Exception)
                }
                .map(SearchAction::GetSearchResult)
                .onEach { dispatcher.dispatch(it) }
                .launchIn(scope)
        }
    }

}