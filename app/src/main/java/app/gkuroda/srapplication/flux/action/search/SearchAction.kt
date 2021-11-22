package app.gkuroda.srapplication.flux.action.search

import app.gkuroda.srapplication.flux.action.Action
import app.gkuroda.srapplication.flux.api.SearchResponse
import com.github.kittinunf.result.Result
import java.lang.Exception

sealed class SearchAction : Action {
    class GetSearchResult(val result : Result<SearchResponse, Exception>) : SearchAction()
}