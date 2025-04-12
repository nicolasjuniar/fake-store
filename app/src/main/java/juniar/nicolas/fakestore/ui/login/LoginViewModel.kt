package juniar.nicolas.fakestore.ui.login

import androidx.lifecycle.viewModelScope
import juniar.nicolas.fakestore.data.request.LoginRequest
import juniar.nicolas.fakestore.network.FakeStoreRepository
import juniar.nicolas.fakestore.util.BaseViewModel
import juniar.nicolas.fakestore.util.FakeStoreSharedPreference
import kotlinx.coroutines.launch

class LoginViewModel(
    private val fakeStoreRepository: FakeStoreRepository,
    private val fakeStoreSharedPreference: FakeStoreSharedPreference
) : BaseViewModel() {

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            isLoading.postValue(true)
            fakeStoreRepository.login(loginRequest).onResult({
                message.postValue("Login Success")
                isSuccess.postValue(true)
            }, {
                message.postValue(it)
            })
        }
    }
}