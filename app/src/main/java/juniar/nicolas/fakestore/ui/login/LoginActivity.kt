package juniar.nicolas.fakestore.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        setupFieldLogin()
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

    private fun setupFieldLogin() {
        val loginValidation = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val username = viewBinding.etUsername.text.toString().trim()
                val password = viewBinding.etPassword.text.toString().trim()
                viewBinding.btnLogin.isEnabled = username.isNotEmpty() && password.isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        viewBinding.etUsername.addTextChangedListener(loginValidation)
        viewBinding.etPassword.addTextChangedListener(loginValidation)
    }
}