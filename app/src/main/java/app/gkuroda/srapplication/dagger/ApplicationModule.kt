package app.gkuroda.srapplication.dagger

import android.content.Context
import app.gkuroda.srapplication.SRApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun context(application: SRApplication): Context = application

}