package juniar.nicolas.fakestore.network

import juniar.nicolas.fakestore.data.request.LoginRequest

class FakeStoreRepository(private val fakeStoreDataSource: FakeStoreDataSource) {

    suspend fun login(loginRequest: LoginRequest) = fakeStoreDataSource.login(loginRequest)

    suspend fun getProducts() = fakeStoreDataSource.getProducts()

    suspend fun getProduct(idProduct: Int) = fakeStoreDataSource.getProduct(idProduct)

    suspend fun getCategories() = fakeStoreDataSource.getCategories()

    suspend fun getProductsByCategory(categoryName: String) =
        fakeStoreDataSource.getProductsByCategory(categoryName)

    suspend fun getUsers() = fakeStoreDataSource.getUsers()
}