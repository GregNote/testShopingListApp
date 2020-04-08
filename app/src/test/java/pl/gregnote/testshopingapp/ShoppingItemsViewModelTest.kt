package pl.gregnote.testshopingapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import pl.gregnote.testshopingapp.model.ShoppingItem
import pl.gregnote.testshopingapp.model.ShoppingList
import pl.gregnote.testshopingapp.repository.ShoppingRepository
import pl.gregnote.testshopingapp.viewmodel.ShoppingItemsViewModel

class ShoppingItemsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: ShoppingRepository

    @Mock
    lateinit var observer: Observer<List<ShoppingList>>

    lateinit var viewModel: ShoppingItemsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ShoppingItemsViewModel(repository)
    }

    @Test
    fun getShoppingList_isSuccess() {
        //given
        val testResult = MutableLiveData<ShoppingList>(ShoppingList())
        `when`(repository.getShoppingList(ArgumentMatchers.anyInt())).thenReturn(testResult)

        //when
        viewModel.fetchData(ArgumentMatchers.anyInt())

        //then
        assertNotNull(viewModel.shoppingList)
    }

    @Test
    fun getShoppingItems_isSuccess() {
        //given
        val list = arrayListOf(
            ShoppingItem(listId = 1),
            ShoppingItem(listId = 1),
            ShoppingItem(listId = 1),
            ShoppingItem(listId = 1),
            ShoppingItem(listId = 1)
        )

        val testResult = MutableLiveData<List<ShoppingItem>>(list)
        `when`(repository.getShoppingItems(ArgumentMatchers.anyInt())).thenReturn(testResult)

        //when
        viewModel.fetchData(ArgumentMatchers.anyInt())

        //then
        assertNotNull(viewModel.shoppingItems)
    }
}
