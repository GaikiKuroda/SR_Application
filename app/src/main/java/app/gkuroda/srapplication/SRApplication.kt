package app.gkuroda.srapplication

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class SRApplication  : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return  app.gkuroda.srapplication.dagger.DaggerApplicationComponent.factory().create(this)
    }
}