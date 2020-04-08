package pl.gregnote.testshopingapp.viewmodel

import androidx.lifecycle.ViewModel
import pl.gregnote.testshopingapp.App
import pl.gregnote.testshopingapp.di.DaggerApplicationComponent
import pl.gregnote.testshopingapp.model.ShoppingItem
import pl.gregnote.testshopingapp.model.ShoppingList
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import javax.inject.Inject

class ShoppingItemsViewModel : ViewModel() {

    @Inject
    lateinit var repository: ShoppingRepository

    init {
        App.applicationComponent.inject(this)
    }

    fun addNewShoppingItem(listId: Int, name: String) {
        repository.addShoppingItem(listId, name)
    }

    fun updateShoppingList(shoppingList: ShoppingList) {
        repository.updateShoppingList(shoppingList)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun getShoppingList(listId: Int) = repository.getShoppingList(listId)

    fun getShoppingItems(listId: Int) = repository.getShoppingItems(listId)
}
