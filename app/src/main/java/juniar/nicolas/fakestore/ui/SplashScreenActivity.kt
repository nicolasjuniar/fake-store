package juniar.nicolas.fakestore.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import juniar.nicolas.fakestore.databinding.ActivitySplashScreenBinding
import juniar.nicolas.fakestore.ui.login.LoginActivity
import juniar.nicolas.fakestore.ui.main.MainActivity
import juniar.nicolas.fakestore.util.BaseViewBindingActivity
import juniar.nicolas.fakestore.util.FakeStoreSharedPreference
import juniar.nicolas.fakestore.util.openActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SplashScreenActivity : BaseViewBindingActivity<ActivitySplashScreenBinding>() {

    private val fakeStoreSharedPreference: FakeStoreSharedPreference by inject()

    override fun getContentView() = ActivitySplashScreenBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        lifecycleScope.launch {
            delay(1000)
            if (fakeStoreSharedPreference.loggedUsername.isEmpty()) {
                openActivity<LoginActivity>()
            } else {
                openActivity<MainActivity>()
            }
        }
    }
}