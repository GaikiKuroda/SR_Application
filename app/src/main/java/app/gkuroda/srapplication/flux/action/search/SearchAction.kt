package app.gkuroda.srapplication.flux.action.search

import app.gkuroda.srapplication.flux.action.Action

sealed class SearchAction : Action {
    class GetSearchResult(val result : String) : SearchAction()
}