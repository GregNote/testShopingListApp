package pl.gregnote.testshopingapp.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import pl.gregnote.testshopingapp.model.ShoppingItem
import pl.gregnote.testshopingapp.model.ShoppingList

@Database(entities = [ShoppingList::class, ShoppingItem::class], version = 1, exportSchema = false)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao

    abstract fun shoppingItemDao(): ShoppingItemDao

    companion object {

        @Volatile
        private var instance: ShoppingDatabase? = null

        fun getDatabase(context: Context): ShoppingDatabase? {
            if (instance == null) {
                synchronized(ShoppingDatabase::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            ShoppingDatabase::class.java, "shopping_database"
                        ).build()
                    }
                }
            }
            return instance
        }
    }

    @Dao
    interface ShoppingListDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun setShoppingList(shoppingList: ShoppingList)

        @Update
        fun updateShoppingList(shoppingList: ShoppingList)

        @Query("SELECT * from shopping_lists WHERE id==:id")
        fun getShoppingList(id: Int): LiveData<ShoppingList>

        @Query("SELECT * from shopping_lists ORDER BY id ASC")
        fun getShoppingLists(): LiveData<List<ShoppingList>>
    }

    @Dao
    interface ShoppingItemDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun setShoppingItem(shoppingItem: ShoppingItem)

        @Delete
        fun deleteShoppingItem(shoppingItem: ShoppingItem)

        @Query("SELECT * from shopping_items WHERE listId==:listId ORDER BY id ASC")
        fun getShoppingItems(listId: Int): LiveData<List<ShoppingItem>>
    }
}
