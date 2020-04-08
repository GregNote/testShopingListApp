package pl.gregnote.testshopingapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import pl.gregnote.testshopingapp.App
import pl.gregnote.testshopingapp.model.ShoppingList
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import javax.inject.Inject

class ShoppingListsViewModel : ViewModel {

    @Inject
    lateinit var repository: ShoppingRepository

    lateinit var shoppingLists: LiveData<List<ShoppingList>>

    constructor() : super() {
        App.applicationComponent.inject(this)
    }

    constructor(repository: ShoppingRepository) : super() {
        this.repository = repository
    }

    fun fetchData() {
        shoppingLists = repository.getShoppingLists()
    }

    fun addNewShoppingList(name: String) {
        repository.addShoppingList(name)
    }

    fun updateShoppingList(shoppingList: ShoppingList) {
        repository.updateShoppingList(shoppingList)
    }
}
