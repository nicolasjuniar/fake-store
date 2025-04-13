package juniar.nicolas.fakestore.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import juniar.nicolas.fakestore.R
import juniar.nicolas.fakestore.databinding.ActivityMainBinding
import juniar.nicolas.fakestore.ui.login.LoginActivity
import juniar.nicolas.fakestore.ui.main.profile.BottomSheetProfile
import juniar.nicolas.fakestore.util.BaseViewBindingActivity
import juniar.nicolas.fakestore.util.openActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseViewBindingActivity<ActivityMainBinding>() {

    private val viewModel: MainViewModel by viewModel()

    override fun getContentView() = ActivityMainBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        val navView: BottomNavigationView = viewBinding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_product, R.id.navigation_cart
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        viewModel.getLoggedUser()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                viewModel.observeLoggedUser().value?.let {
                    BottomSheetProfile(this, it) {
                        logout()
                    }.show()
                }
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun logout() {
        viewModel.removeLoggedUser()
        openActivity<LoginActivity>(isFinishAffinity = true)
    }
}