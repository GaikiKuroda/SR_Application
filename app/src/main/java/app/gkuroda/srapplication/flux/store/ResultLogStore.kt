package app.gkuroda.srapplication.flux.store

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.result.ResultLogAction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext


interface ResultLogStore {
    val searchResultCount: MutableStateFlow<Long>

    val saveSearchResult: MutableStateFlow<Long>
}


suspend fun Store.subscribeResultLogStore(dispatcher: Dispatcher) {
    withContext(Dispatchers.Default) {
        dispatcher.onImpl<ResultLogAction.GetSearchResultCount>()
            .map {
                it.result
            }
            .collect {
                searchResultCount.value = it
            }
    }

    withContext(Dispatchers.Default) {
        dispatcher.onImpl<ResultLogAction.SaveSearchResultLog>()
            .map {
                it.result
            }
            .collect {
                saveSearchResult.value = it
            }
    }

}