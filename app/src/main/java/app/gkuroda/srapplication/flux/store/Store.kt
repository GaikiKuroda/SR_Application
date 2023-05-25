package app.gkuroda.srapplication.flux.store

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.Action
import app.gkuroda.srapplication.flux.api.SearchResponse
import com.github.kittinunf.result.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch


class Store(private val dispatcher: Dispatcher) : StoreInterface {

    val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default

    val stroeJob = Job()
    val storeScope = CoroutineScope(stroeJob + coroutineDispatcher + CoroutineName("Store"))


    override val searchResult: MutableStateFlow<Result<SearchResponse, Exception>> =
        MutableStateFlow(Result.success(SearchResponse(mutableListOf())))


    init {
        subscribe()
    }

    private fun subscribe() {
        storeScope.launch {
            subscribeSearchStore(dispatcher)
        }
    }

    inline fun <reified T : Action> Dispatcher.onImpl(): Flow<T> =
        on<T>().flowOn(coroutineDispatcher)


}