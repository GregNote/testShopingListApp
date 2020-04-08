package pl.gregnote.testshopingapp.repository

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pl.gregnote.testshopingapp.db.ShoppingDatabase
import pl.gregnote.testshopingapp.model.ShoppingItem
import pl.gregnote.testshopingapp.model.ShoppingList
import kotlin.coroutines.CoroutineContext

class ShoppingRepository(context: Context): CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private val shoppingListDao: ShoppingDatabase.ShoppingListDao

    private val shoppingItemDao: ShoppingDatabase.ShoppingItemDao

    init {
        val db = ShoppingDatabase.getDatabase(context)
        shoppingListDao = db.shoppingListDao()
        shoppingItemDao = db.shoppingItemDao()
    }

    fun getShoppingList(id: Int) = shoppingListDao.getShoppingList(id)

    fun getShoppingLists() = shoppingListDao.getShoppingLists()

    fun addShoppingList(name: String) = setShoppingList(ShoppingList(name = name))

    fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        launch {
            withContext(Dispatchers.IO) {
                shoppingItemDao.deleteShoppingItem(shoppingItem)
            }
        }
    }

    fun updateShoppingList(shoppingList: ShoppingList) {
        launch {
            withContext(Dispatchers.IO) {
                shoppingListDao.updateShoppingList(shoppingList)
            }
        }
    }

    fun setShoppingList(shoppingList: ShoppingList) {
        launch {
            setShoppingListBG(shoppingList)
        }
    }

    private suspend fun setShoppingListBG(shoppingList: ShoppingList){
        withContext(Dispatchers.IO) {
            shoppingListDao.setShoppingList(shoppingList)
        }
    }

    fun getShoppingItems(listId: Int) = shoppingItemDao.getShoppingItems(listId)

    fun addShoppingItem(listId: Int, text: String) =
        setShoppingItem(ShoppingItem(listId = listId, text = text))

    fun setShoppingItem(shoppingItem: ShoppingItem) {
        launch {
            setShoppingItemBG(shoppingItem)
        }
    }

    private suspend fun setShoppingItemBG(shoppingItem: ShoppingItem){
        withContext(Dispatchers.IO) {
            shoppingItemDao.setShoppingItem(shoppingItem)
        }
    }
}
