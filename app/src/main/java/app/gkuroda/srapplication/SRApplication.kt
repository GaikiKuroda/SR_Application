package app.gkuroda.srapplication

import android.app.Application
import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.flux.action.search.SearchActionCreatable
import app.gkuroda.srapplication.flux.action.search.SearchActionCreator
import app.gkuroda.srapplication.flux.repository.search.ApiSearchRepository
import app.gkuroda.srapplication.flux.repository.search.SearchRepository
import app.gkuroda.srapplication.flux.store.Store
import app.gkuroda.srapplication.flux.store.StoreInterface
import app.gkuroda.srapplication.ui.MainActivityViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class SRApplication : Application() {

    private val moduleClass = ApplicationModule()

    //アプリケーション全体のモジュール
    private val appModule = module {
        single { Dispatcher() }
        single { moduleClass.baseApiPath(this@SRApplication) }
    }


    // ネットワーク関連モジュール
    private val networkModule = module {
        single { moduleClass.retrofit() }
    }

    // flux関連のモジュール
    private val fluxModule = module {
        single<SearchRepository> { ApiSearchRepository(get()) }
        single<StoreInterface> { Store(get()) }
        factory<SearchActionCreatable> { SearchActionCreator(get<Dispatcher>(), get()) }
    }

    //viewmodelモジュール
    private val viewModelModule = module {
        viewModel {
            MainActivityViewModel(get(), get())
        }
    }

    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())

        startKoin {
            androidContext(this@SRApplication)
            modules(appModule, networkModule, fluxModule, viewModelModule)
        }
    }
}