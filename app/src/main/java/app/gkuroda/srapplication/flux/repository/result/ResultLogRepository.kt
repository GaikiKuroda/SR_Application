package app.gkuroda.srapplication.flux.repository.result

import app.gkuroda.srapplication.flux.api.SearchResponseItem
import kotlinx.coroutines.flow.Flow

interface ResultLogRepository {
    suspend fun getSearchResultCount(): Flow<Long>

    suspend fun saveSearchResult(resultList: List<SearchResponseItem>): Flow<Long>
}