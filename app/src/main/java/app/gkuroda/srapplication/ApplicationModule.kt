package app.gkuroda.srapplication

import android.content.Context
import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.search.SearchActionCreatable
import app.gkuroda.srapplication.flux.action.search.SearchActionCreator
import app.gkuroda.srapplication.flux.repository.search.ApiSearchRepository
import app.gkuroda.srapplication.flux.repository.search.SearchRepository
import app.gkuroda.srapplication.flux.store.Store
import app.gkuroda.srapplication.flux.store.StoreInterface
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

class ApplicationModule {
    fun baseApiPath(context: Context): String =
        context.getString(R.string.github_api_base_url)

    private fun okHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    fun retrofit(): Retrofit {
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .client(okHttpClient())
            .build()
    }


    fun store(dispatcher: Dispatcher): StoreInterface = Store(dispatcher)

    fun searchActionCreator(
        dispatcher: Dispatcher,
        searchRepository: SearchRepository
    ): SearchActionCreatable = SearchActionCreator(dispatcher, searchRepository)

    fun searchRepository(retrofit: Retrofit): SearchRepository = ApiSearchRepository(retrofit)


}