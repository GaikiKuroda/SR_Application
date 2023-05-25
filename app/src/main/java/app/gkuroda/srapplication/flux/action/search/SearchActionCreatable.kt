package app.gkuroda.srapplication.flux.action.search

interface SearchActionCreatable {
    /**
     * 検索のリクエストを行います
     */
    fun getSearchResult(queryString: String)

    fun onDestroy()
}