package app.gkuroda.srapplication.flux.realm

import app.gkuroda.srapplication.flux.model.SearchResponseItemInterface
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class RealmGithubSearchResult : RealmObject, SearchResponseItemInterface {
    @PrimaryKey
    var _id: Long = 0

    override var name: String = ""
}