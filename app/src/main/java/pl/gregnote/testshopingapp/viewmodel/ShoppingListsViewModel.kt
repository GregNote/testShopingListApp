package pl.gregnote.testshopingapp.viewmodel

import androidx.lifecycle.ViewModel
import pl.gregnote.testshopingapp.App
import pl.gregnote.testshopingapp.di.DaggerApplicationComponent
import pl.gregnote.testshopingapp.model.ShoppingList
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import javax.inject.Inject

class ShoppingListsViewModel : ViewModel() {

    @Inject
    lateinit var repository: ShoppingRepository

    init {
        App.applicationComponent.inject(this)
    }

    fun getShoppingLists() = repository.getShoppingLists()

    fun addNewShoppingList(name: String) {
        repository.addShoppingList(name)
    }

    fun updateShoppingList(shoppingList: ShoppingList) {
        repository.updateShoppingList(shoppingList)
    }
}
