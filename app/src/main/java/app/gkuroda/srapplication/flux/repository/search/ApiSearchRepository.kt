package app.gkuroda.srapplication.flux.repository.search

import app.gkuroda.srapplication.flux.api.SearchApi
import app.gkuroda.srapplication.flux.api.SearchResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit

class ApiSearchRepository(retrofit: Retrofit) : SearchRepository {

    private val searchApi: SearchApi = retrofit.create(SearchApi::class.java)

    override fun getSearchResult(queryString: String): Single<SearchResponse> {
        return searchApi.getSearchResult(queryString)
    }
}