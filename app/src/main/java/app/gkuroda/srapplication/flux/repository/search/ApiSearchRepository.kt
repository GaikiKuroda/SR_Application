package app.gkuroda.srapplication.flux.repository.search

import io.reactivex.rxjava3.core.Single

class ApiSearchRepository : SearchRepository {
    override fun getSampleIntItem(item: Int): Single<Int> {
        val new = item + 1
        return Single.just(new)
    }
}