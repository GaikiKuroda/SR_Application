package app.gkuroda.srapplication.flux.api

import app.gkuroda.srapplication.flux.model.SearchResponseItemInterface
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponse(
    @SerialName("items")
    val items: List<SearchResponseItem>
)

@Serializable
data class SearchResponseItem(
    @SerialName("name")
    override var name: String
) : SearchResponseItemInterface