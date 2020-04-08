package pl.gregnote.testshopingapp.model

import androidx.room.*

@Entity(
    tableName = "shopping_items",
    foreignKeys = [ForeignKey(
        entity = ShoppingList::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("listId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class ShoppingItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "listId")
    var listId: Int,

    @ColumnInfo(name = "text")
    var text: String = "",

    @ColumnInfo(name = "count")
    var count: Int = 1
)
