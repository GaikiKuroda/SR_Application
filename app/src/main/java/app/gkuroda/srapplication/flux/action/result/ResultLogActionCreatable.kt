package app.gkuroda.srapplication.flux.action.result

import app.gkuroda.srapplication.flux.api.SearchResponseItem

interface ResultLogActionCreatable {
    fun getSearchResultCount()

    fun saveSearchResultLog(resultList: List<SearchResponseItem>)

    fun onDestroy()
}