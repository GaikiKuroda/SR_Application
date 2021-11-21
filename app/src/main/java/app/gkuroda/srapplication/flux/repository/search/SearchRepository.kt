package app.gkuroda.srapplication.flux.repository.search

import io.reactivex.rxjava3.core.Single

interface SearchRepository {
    fun getSearchResult(queryString: String): Single<String>
}