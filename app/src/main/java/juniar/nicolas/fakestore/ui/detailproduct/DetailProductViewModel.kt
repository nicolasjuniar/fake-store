package juniar.nicolas.fakestore.ui.detailproduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import juniar.nicolas.fakestore.data.local.ProductDao
import juniar.nicolas.fakestore.data.model.ProductModel
import juniar.nicolas.fakestore.network.FakeStoreRepository
import juniar.nicolas.fakestore.util.BaseViewModel
import juniar.nicolas.fakestore.util.FakeStoreSharedPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailProductViewModel(
    private val fakeStoreRepository: FakeStoreRepository,
    private val fakeStoreSharedPreference: FakeStoreSharedPreference,
    private val productDao: ProductDao
) : BaseViewModel() {

    private val product = MutableLiveData<ProductModel>()

    fun observeProduct() = product

    fun getProductById(id: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)
            fakeStoreRepository.getProduct(id).onResult({
                product.postValue(it)
            }, {
                message.postValue(it)
            })
        }
    }

    fun addToCart() {
        viewModelScope.launch(Dispatchers.IO) {
            observeProduct().value?.let {
                productDao.insertSingleProduct(
                    it.toProductLocal(
                        1,
                        fakeStoreSharedPreference.loggedUsername
                    )
                )
            }
        }
    }
}