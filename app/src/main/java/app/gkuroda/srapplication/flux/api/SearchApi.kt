package app.gkuroda.srapplication.flux.api

import io.reactivex.rxjava3.core.Single
import retrofit2.http.POST
import retrofit2.http.Query

interface SearchApi {
    @POST("/search/repositories")
    fun getSearchResult(@Query("q") query: String): Single<String>
}