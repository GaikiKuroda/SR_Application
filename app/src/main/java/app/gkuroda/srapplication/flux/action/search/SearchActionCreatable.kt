package app.gkuroda.srapplication.flux.action.search

interface SearchActionCreatable {
    fun getSearchResult(queryString: String)
}