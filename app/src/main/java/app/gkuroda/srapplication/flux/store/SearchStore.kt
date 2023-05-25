package app.gkuroda.srapplication.flux.store

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.search.SearchAction
import app.gkuroda.srapplication.flux.api.SearchResponse
import com.github.kittinunf.result.Result
import com.orhanobut.logger.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext

interface SearchStore {
    val searchResult: MutableStateFlow<Result<SearchResponse, Exception>>
}


suspend fun Store.subscribeSearchStore(dispatcher: Dispatcher) {
    withContext(Dispatchers.Default) {
        dispatcher.onImpl<SearchAction.GetSearchResult>()
            .map {
                it.result
            }
            .collect {
                searchResult.value = it
            }
    }


}

