package pl.gregnote.testshopingapp.di

import android.app.Application
import android.content.Context
import dagger.Component
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import pl.gregnote.testshopingapp.viewmodel.ShoppingItemsViewModel
import pl.gregnote.testshopingapp.viewmodel.ShoppingListsViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ShoppingRepositoryModule::class,
    ApplicationModule::class
])
interface ApplicationComponent {
    fun provideShoppingRepository(): ShoppingRepository
    fun inject(viewModel: ShoppingItemsViewModel)
    fun inject(viewModel: ShoppingListsViewModel)

    @ApplicationContext
    fun getContext(): Context

    fun getApplication(): Application
}
