package pl.gregnote.testshopingapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import javax.inject.Singleton

@Module
class ShoppingRepositoryModule(
    @ApplicationContext
    val context: Context
) {

    @Provides
    @Singleton
    fun provideShoppingRepository(): ShoppingRepository {
        return ShoppingRepository(context)
    }
}
