package pl.gregnote.testshopingapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import javax.inject.Singleton

@Module
object ShoppingRepositoryModule {

    @Provides
    @Singleton
    fun provideShoppingRepository(@ApplicationContext context: Context): ShoppingRepository {
        return ShoppingRepository(context)
    }
}
