package pl.gregnote.testshopingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.gregnote.testshopingapp.App
import pl.gregnote.testshopingapp.model.ShoppingItem
import pl.gregnote.testshopingapp.model.ShoppingList
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import javax.inject.Inject

class ShoppingItemsViewModel : ViewModel {

    @Inject
    lateinit var repository: ShoppingRepository

    lateinit var shoppingList: LiveData<ShoppingList>
    lateinit var shoppingItems: LiveData<List<ShoppingItem>>

    constructor() : super() {
        App.applicationComponent.inject(this)
    }

    constructor(repository: ShoppingRepository) : super() {
        this.repository = repository
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

    fun fetchData(listId: Int) {
        shoppingList = repository.getShoppingList(listId)
        shoppingItems = repository.getShoppingItems(listId)
    }
}
