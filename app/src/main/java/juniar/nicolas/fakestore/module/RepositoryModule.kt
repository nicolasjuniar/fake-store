package juniar.nicolas.fakestore.module

import juniar.nicolas.fakestore.network.FakeStoreApi
import juniar.nicolas.fakestore.network.FakeStoreDataSource
import juniar.nicolas.fakestore.network.FakeStoreRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val repositoryModule = module {
    single {
        val apiService = get<Retrofit>().create(FakeStoreApi::class.java)
        val remoteDataSource = FakeStoreDataSource(apiService)
        FakeStoreRepository(remoteDataSource)
    }
}