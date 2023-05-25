package app.gkuroda.srapplication.flux

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel

abstract class IOScopeSensitiveActionCreator : LifecycleSensitive {
    protected val scope =
        CoroutineScope(Job() + Dispatchers.IO + CoroutineName("IOScopeSensitiveActionCreator"))

    override fun onDestroy() = scope.cancel()
}