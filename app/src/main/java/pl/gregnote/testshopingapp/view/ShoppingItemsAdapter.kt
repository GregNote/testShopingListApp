package pl.gregnote.testshopingapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_item_row.view.*
import pl.gregnote.testshopingapp.R
import pl.gregnote.testshopingapp.model.ShoppingItem
import pl.gregnote.testshopingapp.model.ShoppingList

class ShoppingItemsAdapter(
    private val shoppingItems: ArrayList<ShoppingItem>,
    private val onDelete: (shoppingList: ShoppingItem) -> Unit
) : RecyclerView.Adapter<ShoppingItemsAdapter.ShoppingItemViewHolder>() {

    var isEnabled = true
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun updateItems(newItems: List<ShoppingItem>) {
        shoppingItems.clear()
        shoppingItems.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        return ShoppingItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_item_row, parent, false))
    }

    override fun getItemCount(): Int = shoppingItems.size

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        holder.bind(shoppingItems[position], isEnabled, onDelete)
    }

    class ShoppingItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.name
        private val deleteBtn = view.deleteBtn

        fun bind(shoppingItem: ShoppingItem, isEnabled: Boolean, onDelete: (shoppingList: ShoppingItem) -> Unit) {
            name.text = shoppingItem.text
            if (isEnabled) {
                deleteBtn.setOnClickListener {
                    onDelete(shoppingItem)
                }
                deleteBtn.visibility = View.VISIBLE
            } else {
                deleteBtn.visibility = View.INVISIBLE
            }
        }
    }
}
