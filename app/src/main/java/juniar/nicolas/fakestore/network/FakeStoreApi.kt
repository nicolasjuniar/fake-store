package juniar.nicolas.fakestore.network

import juniar.nicolas.fakestore.data.request.LoginRequest
import juniar.nicolas.fakestore.data.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeStoreApi {

    @GET("products")
    suspend fun getProducts(): Response<String>

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>
}