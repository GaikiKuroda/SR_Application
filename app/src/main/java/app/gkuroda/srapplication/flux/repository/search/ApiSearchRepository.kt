package app.gkuroda.srapplication.flux.repository.search

import app.gkuroda.srapplication.flux.api.SearchApi
import app.gkuroda.srapplication.flux.api.SearchResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit

class ApiSearchRepository(retrofit: Retrofit) : SearchRepository {

    private val searchApi: SearchApi = retrofit.create(SearchApi::class.java)

    override suspend fun getSearchResult(queryString: String): Flow<SearchResponse> {
        return flow {
            emit(searchApi.getSearchResult(queryString))
        }
    }
}