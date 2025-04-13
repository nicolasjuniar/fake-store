package juniar.nicolas.fakestore.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import juniar.nicolas.fakestore.data.model.UserModel
import juniar.nicolas.fakestore.network.FakeStoreRepository
import juniar.nicolas.fakestore.util.BaseViewModel
import juniar.nicolas.fakestore.util.FakeStoreSharedPreference
import kotlinx.coroutines.launch

class MainViewModel(
    private val fakeStoreRepository: FakeStoreRepository,
    private val fakeStoreSharedPreference: FakeStoreSharedPreference
) : BaseViewModel() {

    private val loggedUser = MutableLiveData<UserModel>()

    fun observeLoggedUser() = loggedUser

    fun getLoggedUser() {
        viewModelScope.launch {
            isLoading.postValue(true)
            fakeStoreRepository.getUsers().onResult({
                it.forEach { user ->
                    if (user.username == fakeStoreSharedPreference.loggedUsername) {
                        loggedUser.postValue(user)
                    }
                }
            }, {
                message.postValue(it)
            })
        }
    }

    fun removeLoggedUser() {
        fakeStoreSharedPreference.loggedUsername = ""
    }
}