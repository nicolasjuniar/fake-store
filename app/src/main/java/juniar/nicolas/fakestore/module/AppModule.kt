package juniar.nicolas.fakestore.module

import android.content.Context
import androidx.room.Room
import juniar.nicolas.fakestore.data.local.AppDatabase
import juniar.nicolas.fakestore.data.local.ProductDao
import juniar.nicolas.fakestore.util.Constant
import juniar.nicolas.fakestore.util.DiffCallback
import juniar.nicolas.fakestore.util.FakeStoreSharedPreference
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { DiffCallback() }
    single {
        FakeStoreSharedPreference(
            androidContext().getSharedPreferences(
                Constant.FAKE_STORE_SHARED_PREFERENCE,
                Context.MODE_PRIVATE
            )
        )
    }

    single {
        Room.databaseBuilder(
            context = get(),
            AppDatabase::class.java, "database-product"
        ).build()
    }

    single<ProductDao> { get<AppDatabase>().productDao() }
}