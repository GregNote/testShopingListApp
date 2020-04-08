package pl.gregnote.testshopingapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.gregnote.testshopingapp.App
import javax.inject.Singleton


@Module
open class ApplicationModule(val app: App) {

    @Singleton
    @Provides
    @ApplicationContext
    open fun provideContext(): Context {
        return app
    }

    @Singleton
    @Provides
    open fun provideApplication(): Application {
        return app
    }
}
