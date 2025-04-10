package juniar.nicolas.fakestore.ui.auth

import android.os.Bundle
import androidx.core.view.isVisible
import juniar.nicolas.fakestore.util.BaseViewBindingActivity
import juniar.nicolas.fakestore.R
import juniar.nicolas.fakestore.databinding.ActivityAuthBinding

class AuthActivity : BaseViewBindingActivity<ActivityAuthBinding>() {

    override fun getContentView() = ActivityAuthBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        setupListener()
    }

    private fun setupListener() {
        with(viewBinding) {
            loginContainer.setOnClickListener {
                setupLayout(true)
            }
            registerContainer.setOnClickListener {
                setupLayout(false)
            }
        }
    }

    private fun setupLayout(isLogin: Boolean) {
        with(viewBinding) {
            loginContainer.background = if (isLogin) {
                getDrawable(R.drawable.rounded_bg_left_purple_500)
            } else {
                getDrawable(R.drawable.rounded_bg_left_purple_500_grayout)
            }
            registerContainer.background = if (isLogin) {
                getDrawable(R.drawable.rounded_bg_right_purple_500_grayout)
            } else {
                getDrawable(R.drawable.rounded_bg_right_purple_500)
            }
            emailContainer.isVisible = !isLogin
            ivIconEmail.isVisible = !isLogin
            etUsername.setText("")
            etEmail.setText("")
            etPassword.setText("")
            btnAction.text = if (isLogin) {
                "LOGIN"
            } else {
                "REGISTER"
            }
            btnAction.isEnabled = false
        }
    }
}