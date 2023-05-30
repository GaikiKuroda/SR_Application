package app.gkuroda.srapplication.flux.action.result

import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.IOScopeSensitiveActionCreator
import app.gkuroda.srapplication.flux.api.SearchResponseItem
import app.gkuroda.srapplication.flux.repository.result.ResultLogRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ResultLogActionCreator(
    private val dispatcher: Dispatcher,
    private val resultLogRepository: ResultLogRepository
) : IOScopeSensitiveActionCreator(), ResultLogActionCreatable {
    override fun getSearchResultCount() {
        scope.launch {
            resultLogRepository.getSearchResultCount()
                .map(ResultLogAction::GetSearchResultCount)
                .onEach { dispatcher.dispatch(it) }
                .launchIn(scope)
        }
    }

    override fun saveSearchResultLog(resultList: List<SearchResponseItem>) {
        scope.launch {
            resultLogRepository.saveSearchResult(resultList)
                .map(ResultLogAction::SaveSearchResultLog)
                .onEach { dispatcher.dispatch(it) }
                .launchIn(scope)
        }
    }

}