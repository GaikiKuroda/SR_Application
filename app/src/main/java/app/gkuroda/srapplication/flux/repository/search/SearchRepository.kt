package app.gkuroda.srapplication.flux.repository.search

import app.gkuroda.srapplication.flux.api.SearchResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchResult(queryString: String): Flow<SearchResponse>
}