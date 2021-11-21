package app.gkuroda.srapplication.ui

import androidx.lifecycle.ViewModel
import app.gkuroda.srapplication.flux.action.search.SearchActionCreatable
import app.gkuroda.srapplication.flux.api.SearchResponse
import app.gkuroda.srapplication.flux.store.StoreInterface
import com.jakewharton.rxrelay3.BehaviorRelay
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val store: StoreInterface,
    private val searchActionCreator: SearchActionCreatable
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val searchResult: BehaviorRelay<SearchResponse> = BehaviorRelay.create()

    init {
        subscribeStore()
    }

    /**
     * Storeの更新を購読します
     */
    private fun subscribeStore() {
        store.searchResult.subscribe {
            searchResult.accept(it)
        }.addTo(disposable)

        //Todo:エラーハンドリング
    }

    /**
     * 検索のリクエストを行います
     */
    fun requestSearch(query: String) {
        searchActionCreator.getSearchResult(query)
    }

}