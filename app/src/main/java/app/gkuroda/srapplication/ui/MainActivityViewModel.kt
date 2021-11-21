package app.gkuroda.srapplication.ui

import androidx.lifecycle.ViewModel
import app.gkuroda.srapplication.flux.store.StoreInterface
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val store: StoreInterface
):ViewModel() {
}