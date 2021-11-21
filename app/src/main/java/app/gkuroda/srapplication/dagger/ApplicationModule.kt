package app.gkuroda.srapplication.dagger

import android.content.Context
import app.gkuroda.srapplication.SRApplication
import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.search.SearchActionCreatable
import app.gkuroda.srapplication.flux.action.search.SearchActionCreator
import app.gkuroda.srapplication.flux.repository.search.ApiSearchRepository
import app.gkuroda.srapplication.flux.repository.search.SearchRepository
import app.gkuroda.srapplication.flux.store.Store
import app.gkuroda.srapplication.flux.store.StoreInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    fun context(application: SRApplication): Context = application

    @Provides
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder().build()

    @Singleton
    @Provides
    fun store(dispatcher: Dispatcher): StoreInterface = Store(dispatcher)

    @Provides
    fun searchActionCreator(
        dispatcher: Dispatcher,
        searchRepository: SearchRepository
    ): SearchActionCreatable = SearchActionCreator(dispatcher, searchRepository)

    @Singleton
    @Provides
    fun searchRepository(): SearchRepository = ApiSearchRepository()
}