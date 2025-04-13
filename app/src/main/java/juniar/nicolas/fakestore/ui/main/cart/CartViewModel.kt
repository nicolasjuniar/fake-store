package juniar.nicolas.fakestore.ui.main.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import juniar.nicolas.fakestore.data.local.ProductDao
import juniar.nicolas.fakestore.data.model.ProductLocal
import juniar.nicolas.fakestore.util.BaseViewModel
import juniar.nicolas.fakestore.util.FakeStoreSharedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(
    private val productDao: ProductDao,
    private val fakeStoreSharedPreference: FakeStoreSharedPreference
) : BaseViewModel() {

    private val listProduct = MutableLiveData<List<ProductLocal>>()

    fun observeListProduct() = listProduct

    fun getListCart() {
        viewModelScope.launch(Dispatchers.IO) {
            listProduct.postValue(productDao.getAllProduct(fakeStoreSharedPreference.loggedUsername))
        }
    }

    fun checkout() {
        viewModelScope.launch(Dispatchers.IO) {
            productDao.deleteAllProduct(fakeStoreSharedPreference.loggedUsername)
            message.postValue("You successfuly checkout all items")
            getListCart()
        }
    }

    fun updateQuantity(quantity: Int, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (quantity == 0) {
                productDao.deleteSingleProduct(id)
            } else {
                productDao.updateQuantity(quantity, id)
            }
            getListCart()
        }
    }
}