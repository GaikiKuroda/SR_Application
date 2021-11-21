package app.gkuroda.srapplication.dagger

import android.content.Context
import app.gkuroda.srapplication.R
import app.gkuroda.srapplication.SRApplication
import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.search.SearchActionCreatable
import app.gkuroda.srapplication.flux.action.search.SearchActionCreator
import app.gkuroda.srapplication.flux.repository.search.ApiSearchRepository
import app.gkuroda.srapplication.flux.repository.search.SearchRepository
import app.gkuroda.srapplication.flux.store.Store
import app.gkuroda.srapplication.flux.store.StoreInterface
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule {
    @Provides
    fun context(application: SRApplication): Context = application

    @Provides
    fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Singleton
    @Provides
    fun baseApiPath(context: Context): String = context.getString(R.string.github_api_base_url)

    @Singleton
    @Provides
    fun moshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun retrofit(okHttpClient: OkHttpClient, moshi: Moshi, baseApiPath: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseApiPath)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

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
    fun searchRepository(retrofit: Retrofit): SearchRepository = ApiSearchRepository(retrofit)
}