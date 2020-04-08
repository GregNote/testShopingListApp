package pl.gregnote.testshopingapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_shopping_item.*
import pl.gregnote.testshopingapp.R
import pl.gregnote.testshopingapp.model.ShoppingItem
import pl.gregnote.testshopingapp.util.getDialogWithEdit
import pl.gregnote.testshopingapp.viewmodel.ShoppingItemsViewModel

class ShoppingItemsFragment : Fragment() {

    private lateinit var viewModel: ShoppingItemsViewModel
    private val adapter = ShoppingItemsAdapter(
        arrayListOf(),
        this::onDelete
    )

    private fun onDelete(shoppingItem: ShoppingItem) {
        viewModel.deleteShoppingItem(shoppingItem)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("listId")?.also { listId ->
            itemsRecyclerView.adapter = adapter
            viewModel = ViewModelProviders.of(this).get(ShoppingItemsViewModel::class.java)

            viewModel.getShoppingList(listId)?.observe(viewLifecycleOwner, Observer { shoppingList ->
                nameEdit.setText(shoppingList.name)
                nameEdit.isEnabled = !shoppingList.archived
                name.setEndIconOnClickListener {
                    shoppingList.name = nameEdit.text.toString()
                    viewModel.updateShoppingList(shoppingList)
                }
                name.isEnabled = !shoppingList.archived
                adapter.isEnabled = !shoppingList.archived

                if (shoppingList.archived) {
                    addNewItem.visibility = View.GONE
                } else {
                    addNewItem.setOnClickListener {
                        getDialogWithEdit(it.context, "Type name") {
                            viewModel.addNewShoppingItem(listId, it)
                        }
                    }
                }
            })

            viewModel.getShoppingItems(listId)?.observe(viewLifecycleOwner, Observer {
                adapter.updateItems(it)
            })
        }
    }
}
