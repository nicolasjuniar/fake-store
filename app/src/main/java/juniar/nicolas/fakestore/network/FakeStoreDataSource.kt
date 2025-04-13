package juniar.nicolas.fakestore.network

import juniar.nicolas.fakestore.data.request.LoginRequest
import juniar.nicolas.fakestore.util.BaseDataSource

class FakeStoreDataSource(private val fakeStoreApi: FakeStoreApi) : BaseDataSource() {

    suspend fun login(loginRequest: LoginRequest) = suspendDataResult {
        getResult {
            fakeStoreApi.login(loginRequest)
        }
    }

    suspend fun getProducts() = suspendDataResult {
        getResult {
            fakeStoreApi.getProducts()
        }
    }

    suspend fun getProduct(idProduct: Int) = suspendDataResult {
        getResult {
            fakeStoreApi.getProduct(idProduct)
        }
    }

    suspend fun getCategories() = suspendDataResult {
        getResult {
            fakeStoreApi.getCategories()
        }
    }

    suspend fun getProductsByCategory(categoryName: String) = suspendDataResult {
        getResult {
            fakeStoreApi.getProductsByCategory(categoryName)
        }
    }

    suspend fun getUsers() = suspendDataResult {
        getResult {
            fakeStoreApi.getUsers()
        }
    }
}