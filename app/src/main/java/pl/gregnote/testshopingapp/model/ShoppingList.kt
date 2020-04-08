package pl.gregnote.testshopingapp.model

import androidx.room.*

@Entity(tableName = "shopping_lists")
data class ShoppingList(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "archived")
    var archived: Boolean = false
)
