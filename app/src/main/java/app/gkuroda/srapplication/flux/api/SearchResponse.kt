package app.gkuroda.srapplication.flux.api

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
    val name: String
)