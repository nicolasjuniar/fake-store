package juniar.nicolas.fakestore.ui.main.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import juniar.nicolas.fakestore.data.local.ProductDao
import juniar.nicolas.fakestore.data.model.ProductLocal
import juniar.nicolas.fakestore.util.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CartViewModel(private val productDao: ProductDao) : BaseViewModel() {

    private val listProduct = MutableLiveData<List<ProductLocal>>()

    fun observeListProduct() = listProduct

    fun getListCart() {
        viewModelScope.launch(Dispatchers.IO) {
            listProduct.postValue(productDao.getAllProduct())
        }
    }
}