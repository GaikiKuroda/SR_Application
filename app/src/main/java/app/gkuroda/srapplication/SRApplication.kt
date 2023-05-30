package app.gkuroda.srapplication

import android.app.Application
import app.gkuroda.srapplication.flux.Dispatcher
import app.gkuroda.srapplication.ui.MainActivityViewModel
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.realm.kotlin.Realm
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
        single { moduleClass.realm() }
        single { moduleClass.store(get()) }
    }

    // ネットワーク関連モジュール
    private val networkModule = module {
        single { moduleClass.retrofit() }
    }

    //  repository関連のモジュール
    private val repositoryModule = module {
        single { moduleClass.searchRepository(get()) }
        single { moduleClass.resultLogRepository(get() as Realm) }
    }

    //  action関連のモジュール
    private val actionModule = module {
        factory { moduleClass.searchActionCreator(get(), get()) }
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
            modules(appModule, networkModule, repositoryModule, actionModule, viewModelModule)
        }
    }
}