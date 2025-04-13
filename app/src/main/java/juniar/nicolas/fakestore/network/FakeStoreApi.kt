package juniar.nicolas.fakestore.network

import juniar.nicolas.fakestore.data.model.ProductModel
import juniar.nicolas.fakestore.data.model.UserModel
import juniar.nicolas.fakestore.data.request.LoginRequest
import juniar.nicolas.fakestore.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FakeStoreApi {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @GET("products")
    suspend fun getProducts(): Response<List<ProductModel>>

    @GET("products/{id_product}")
    suspend fun getProduct(@Path("id_product") idProduct: Int): Response<ProductModel>

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("products/category/{category_name}")
    suspend fun getProductsByCategory(@Path("category_name") categoryName: String)
            : Response<List<ProductModel>>

    @GET("users")
    suspend fun getUsers(): Response<List<UserModel>>
}