package app.gkuroda.srapplication.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.gkuroda.srapplication.flux.action.search.SearchActionCreatable
import app.gkuroda.srapplication.flux.api.SearchResponse
import app.gkuroda.srapplication.flux.store.StoreInterface
import com.github.kittinunf.result.Result
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivityViewModel(
    private val store: StoreInterface,
    private val searchActionCreator: SearchActionCreatable
) : ViewModel() {

    // 検索結果連携用
    val searchResult: MutableLiveData<Result<SearchResponse, Exception>> = MutableLiveData()

    init {
        subscribeStore()
    }

    /**
     * Storeの更新を購読します
     */
    private fun subscribeStore() {
        store.searchResult
            .drop(1)
            .onEach { result ->
                searchResult.value = result
            }
            .launchIn(viewModelScope)

    }

    /**
     * 検索のリクエストを行います
     */
    fun requestSearch(query: String) {
        searchActionCreator.getSearchResult(query)
    }

    override fun onCleared() {
        super.onCleared()
        searchActionCreator.onDestroy()
    }

}