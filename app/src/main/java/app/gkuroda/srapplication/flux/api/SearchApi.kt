package app.gkuroda.srapplication.flux.api

import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("/search/repositories")
    suspend fun getSearchResult(@Query("q") query: String): SearchResponse
}