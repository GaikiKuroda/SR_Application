package app.gkuroda.srapplication.flux.action.result

import app.gkuroda.srapplication.flux.action.Action

sealed class ResultLogAction : Action {
    class GetSearchResultCount(val result: Long) : ResultLogAction()

    class SaveSearchResultLog(val result: Long) : ResultLogAction()
}