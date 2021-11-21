package app.gkuroda.srapplication.flux.action.search

import app.gkuroda.srapplication.flux.action.Action

sealed class SearchAction : Action {
    class GetSampleIntItem(val result: Int) : SearchAction()
}