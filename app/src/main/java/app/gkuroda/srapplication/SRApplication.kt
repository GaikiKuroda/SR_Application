package app.gkuroda.srapplication

import android.app.Application
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
    private val appModule by lazy { moduleClass.createAppModule(this.applicationContext) }

    // ネットワーク関連モジュール
    private val networkModule by lazy { moduleClass.createNetworkModule() }

    //  repository関連のモジュール
    private val repositoryModule by lazy { moduleClass.createRepositoryModule() }

    //  action関連のモジュール
    private val actionModule by lazy { moduleClass.createActionModule() }

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