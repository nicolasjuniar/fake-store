package juniar.nicolas.fakestore.ui.main.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import juniar.nicolas.fakestore.data.model.ProductModel
import juniar.nicolas.fakestore.network.FakeStoreRepository
import juniar.nicolas.fakestore.util.BaseViewModel
import kotlinx.coroutines.launch

class ProductViewModel(
    private val fakeStoreRepository: FakeStoreRepository,
) : BaseViewModel() {

    private val listProduct = MutableLiveData<List<ProductModel>>()

    private val listCategory = MutableLiveData<List<String>>()

    fun observeListProduct() = listProduct

    fun observeListCategory() = listCategory

    fun getProducts() {
        viewModelScope.launch {
            isLoading.postValue(true)
            fakeStoreRepository.getProducts().onResult({
                listProduct.postValue(it)
            }, {
                message.postValue(it)
            })
        }
    }

    fun getCategories() {
        viewModelScope.launch {
            fakeStoreRepository.getCategories().onResult({
                listCategory.postValue(it)
            }, {
                message.postValue(it)
            })
        }
    }

    fun getProductsByCategory(categoryName: String) {
        viewModelScope.launch {
            fakeStoreRepository.getProductsByCategory(categoryName).onResult({
                listProduct.postValue(it)
            }, {
                message.postValue(it)
            })
        }
    }
}