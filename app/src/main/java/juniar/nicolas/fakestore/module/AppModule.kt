package juniar.nicolas.fakestore.module

import android.content.Context
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
}