package app.gkuroda.srapplication.flux.repository.result

import app.gkuroda.srapplication.flux.api.SearchResponseItem
import app.gkuroda.srapplication.flux.realm.RealmGithubSearchResult
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RealmResultLogRepository(private val realm: Realm) : ResultLogRepository {


    override suspend fun getSearchResultCount(): Flow<Long> {
        return realm.query(RealmGithubSearchResult::class).count().asFlow()
    }

    override suspend fun saveSearchResult(resultList: List<SearchResponseItem>): Flow<Long> {
        return flow {
            var id: Long =
                realm.query(RealmGithubSearchResult::class).count().find()

            realm.writeBlocking {
                resultList.forEach {
                    id++
                    this.copyToRealm(RealmGithubSearchResult().apply {
                        _id = id
                        name = it.name
                    }, updatePolicy = UpdatePolicy.ALL)
                }
            }
            emit(id)
        }
    }
}