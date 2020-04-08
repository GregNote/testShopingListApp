package pl.gregnote.testshopingapp.db;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

import pl.gregnote.testshopingapp.model.ShoppingItem;
import pl.gregnote.testshopingapp.model.ShoppingList;

@Database(entities = {ShoppingList.class, ShoppingItem.class}, version = 1, exportSchema = false)
public abstract class ShoppingDatabase extends RoomDatabase {

    public abstract ShoppingListDao shoppingListDao();

    public abstract ShoppingItemDao shoppingItemDao();

    private static volatile ShoppingDatabase instance = null;

    public static ShoppingDatabase getDatabase(Context context) {
        synchronized (ShoppingDatabase.class) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        ShoppingDatabase.class,
                        "shopping_database"
                ).build();
            }
            return instance;
        }
    }

    @Dao
    public interface ShoppingListDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void setShoppingList(ShoppingList shoppingList);

        @Update
        void updateShoppingList(ShoppingList shoppingList);

        @Query("SELECT * from shopping_lists WHERE id==:id")
        LiveData<ShoppingList> getShoppingList(int id);

        @Query("SELECT * from shopping_lists ORDER BY id ASC")
        LiveData<List<ShoppingList>> getShoppingLists();
    }

    @Dao
    public interface ShoppingItemDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        void setShoppingItem(ShoppingItem shoppingItem);

        @Delete
        void deleteShoppingItem(ShoppingItem shoppingItem);

        @Query("SELECT * from shopping_items WHERE listId==:listId ORDER BY id ASC")
        LiveData<List<ShoppingItem>> getShoppingItems(int listId);
    }
}
