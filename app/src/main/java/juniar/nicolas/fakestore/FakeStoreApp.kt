package juniar.nicolas.fakestore

import android.app.Application
import juniar.nicolas.fakestore.module.appModule
import juniar.nicolas.fakestore.module.networkModule
import juniar.nicolas.fakestore.module.repositoryModule
import juniar.nicolas.fakestore.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

class FakeStoreApp: Application() {

    lateinit var koinApplication: KoinApplication

    override fun onCreate() {
        super.onCreate()
        koinApplication = startKoin {
            androidContext(this@FakeStoreApp.applicationContext)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}