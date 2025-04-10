package juniar.nicolas.fakestore.ui.auth

import android.os.Bundle
import androidx.core.view.isVisible
import juniar.nicolas.fakestore.R
import juniar.nicolas.fakestore.data.request.LoginRequest
import juniar.nicolas.fakestore.databinding.ActivityAuthBinding
import juniar.nicolas.fakestore.ui.main.MainActivity
import juniar.nicolas.fakestore.util.BaseViewBindingActivity
import juniar.nicolas.fakestore.util.openActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : BaseViewBindingActivity<ActivityAuthBinding>() {

    private val viewModel: AuthViewModel by viewModel()

    override fun getContentView() = ActivityAuthBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        setupListener()
        observeData()
    }

    private fun setupListener() {
        with(viewBinding) {
            loginContainer.setOnClickListener {
                setupLayout(true)
            }
            registerContainer.setOnClickListener {
                setupLayout(false)
            }
            btnAction.setOnClickListener {
                viewModel.login(
                    LoginRequest(
                        etUsername.text.toString(),
                        etPassword.text.toString()
                    )
                )
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
//            btnAction.isEnabled = false
        }
    }

    private fun observeData() {
        with(viewModel) {
            observeViewModel(this)
            observeSuccess().onChangeValue {
                openActivity<MainActivity>(isFinish = true)
            }
        }
    }
}