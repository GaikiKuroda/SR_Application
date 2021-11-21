package app.gkuroda.srapplication.flux.api

import com.squareup.moshi.Json

data class SearchResponse(
    @Json(name = "items")
    val items: List<SearchResponseItem>
)

data class SearchResponseItem(
    @Json(name = "name")
    val name: String
)