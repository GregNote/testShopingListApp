package pl.gregnote.testshopingapp

import android.app.Application
import pl.gregnote.testshopingapp.di.ApplicationComponent
import pl.gregnote.testshopingapp.di.ApplicationModule
import pl.gregnote.testshopingapp.di.DaggerApplicationComponent
import pl.gregnote.testshopingapp.di.ShoppingRepositoryModule

class App : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .shoppingRepositoryModule(ShoppingRepositoryModule)
            .build()
    }
}
