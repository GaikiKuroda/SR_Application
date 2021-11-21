package app.gkuroda.srapplication.flux.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("/search/repositories")
    fun getSearchResult(@Query("q") query: String): Single<SearchResponse>
}