package app.gkuroda.srapplication.dagger

import app.gkuroda.srapplication.ui.MainActivity
import app.gkuroda.srapplication.ui.SearchResultFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeSearchResultFragment(): SearchResultFragment
}