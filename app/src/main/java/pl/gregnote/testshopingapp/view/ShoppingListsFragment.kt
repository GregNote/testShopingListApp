package pl.gregnote.testshopingapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_shopping_list.*
import pl.gregnote.testshopingapp.R
import pl.gregnote.testshopingapp.model.ShoppingList
import pl.gregnote.testshopingapp.util.getDialogWithEdit
import pl.gregnote.testshopingapp.viewmodel.ShoppingListsViewModel

class ShoppingListsFragment : Fragment() {

    private lateinit var viewModel: ShoppingListsViewModel
    private val adapter = ShoppingListsAdapter(
        arrayListOf(),
        this::onArchive,
        this::onClick)

    private fun onArchive(shoppingList: ShoppingList) {
        shoppingList.archived = true
        viewModel.updateShoppingList(shoppingList)
    }

    private fun onClick(listId: Int) {
        findNavController().navigate(
            R.id.action_listsFragment_to_itemsFragment,
            bundleOf("listId" to listId)
        )
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shopping_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listsRecyclerView.adapter = adapter
        viewModel = ViewModelProviders.of(this).get(ShoppingListsViewModel::class.java)
        viewModel.fetchData()
        viewModel.shoppingLists.observe(viewLifecycleOwner, Observer {
            adapter.updateLists(it)
        })
        addNewList.setOnClickListener {
            getDialogWithEdit(it.context, "Type name") {
                viewModel.addNewShoppingList(it)
            }
        }
    }
}
