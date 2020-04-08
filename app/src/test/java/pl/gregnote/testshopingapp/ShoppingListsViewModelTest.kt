package pl.gregnote.testshopingapp

import androidx.lifecycle.LiveData
import io.reactivex.Single
import org.junit.Assert
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import pl.gregnote.testshopingapp.model.ShoppingList
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import pl.gregnote.testshopingapp.viewmodel.ShoppingListsViewModel

class ShoppingListsViewModelTest {

    @Mock
    lateinit var repository: ShoppingRepository

    @InjectMocks
    var viewModel = ShoppingListsViewModel()

    private var testSingle: Single<List<ShoppingList>>? = null

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun getShoppingLists_isSuccess() {

    }

}
