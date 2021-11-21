package app.gkuroda.srapplication.flux.repository.search

import app.gkuroda.srapplication.flux.api.SearchResponse
import io.reactivex.rxjava3.core.Single

interface SearchRepository {
    fun getSearchResult(queryString: String): Single<SearchResponse>
}