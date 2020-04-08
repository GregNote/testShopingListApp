package pl.gregnote.testshopingapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.shopping_list_row.view.*
import pl.gregnote.testshopingapp.R
import pl.gregnote.testshopingapp.model.ShoppingList

class ShoppingListsAdapter(
    private val shoppingLists: ArrayList<ShoppingList>,
    private val onArchive: (shoppingList: ShoppingList) -> Unit,
    private val onClick: (listId: Int) -> Unit
) : RecyclerView.Adapter<ShoppingListsAdapter.ShoppingListViewHolder>() {

    fun updateLists(newLists: List<ShoppingList>) {
        shoppingLists.clear()
        shoppingLists.addAll(newLists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListViewHolder {
        return ShoppingListViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shopping_list_row, parent, false))
    }

    override fun getItemCount(): Int = shoppingLists.size

    override fun onBindViewHolder(holder: ShoppingListViewHolder, position: Int) {
        holder.bind(shoppingLists[position], onArchive, onClick)
    }

    class ShoppingListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.name
        private val archiveBtn = view.archiveBtn

        fun bind(
            shoppingList: ShoppingList,
            onArchive: (shoppingList: ShoppingList) -> Unit,
            onClick: (listId: Int) -> Unit
        ) {
            name.text = shoppingList.name
            itemView.setOnClickListener { onClick(shoppingList.id) }
            if (shoppingList.archived) {
                itemView.setBackgroundColor(0xffcccccc.toInt())
                archiveBtn.visibility = View.INVISIBLE
            } else {
                itemView.setBackgroundColor(0xffffffff.toInt())
                archiveBtn.visibility = View.VISIBLE
                archiveBtn.setOnClickListener { onArchive(shoppingList) }
            }
        }
    }
}
