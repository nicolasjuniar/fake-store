package juniar.nicolas.fakestore.ui.login

import android.os.Bundle
import juniar.nicolas.fakestore.data.request.LoginRequest
import juniar.nicolas.fakestore.databinding.ActivityLoginBinding
import juniar.nicolas.fakestore.ui.main.MainActivity
import juniar.nicolas.fakestore.util.BaseViewBindingActivity
import juniar.nicolas.fakestore.util.openActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseViewBindingActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModel()

    override fun getContentView() = ActivityLoginBinding.inflate(layoutInflater)

    override fun onViewReady(savedInstanceState: Bundle?) {
        setupListener()
        observeData()
    }

    private fun setupListener() {
        with(viewBinding) {
            btnLogin.setOnClickListener {
                viewModel.login(
                    LoginRequest(
                        etUsername.text.toString(),
                        etPassword.text.toString()
                    )
                )
            }
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