package pl.gregnote.testshopingapp.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.gregnote.testshopingapp.App
import javax.inject.Singleton


@Module
class ApplicationModule(val app: App) {

    @Singleton
    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return app
    }

    @Singleton
    @Provides
    fun provideApplication(): Application {
        return app
    }
}
