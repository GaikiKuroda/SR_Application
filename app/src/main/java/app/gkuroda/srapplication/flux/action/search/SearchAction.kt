package app.gkuroda.srapplication.flux.action.search

import app.gkuroda.srapplication.flux.action.Action
import app.gkuroda.srapplication.flux.api.SearchResponse

sealed class SearchAction : Action {
    class GetSearchResult(val result : SearchResponse) : SearchAction()
}