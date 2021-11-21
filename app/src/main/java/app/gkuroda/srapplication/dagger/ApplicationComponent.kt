package app.gkuroda.srapplication.dagger

import app.gkuroda.srapplication.SRApplication
import app.gkuroda.srapplication.dagger.viewModel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ApplicationModule::class,
        ViewModelModule::class
    ]
)

interface ApplicationComponent : AndroidInjector<SRApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<SRApplication>

}