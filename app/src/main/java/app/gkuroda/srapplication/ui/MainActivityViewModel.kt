package app.gkuroda.srapplication.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import app.gkuroda.srapplication.flux.action.search.SearchActionCreatable
import app.gkuroda.srapplication.flux.store.StoreInterface
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val store: StoreInterface,
    private val searchActionCreator: SearchActionCreatable
) : ViewModel() {

    private val disposable = CompositeDisposable()

    init {
        subsccribeStore()
    }

    fun subsccribeStore() {
        store.searchResult.subscribe {
            Log.e("tag","$it")
        }.addTo(disposable)
    }

    fun requestSearch() {
        searchActionCreator.getSearchResult("android")

    }

}