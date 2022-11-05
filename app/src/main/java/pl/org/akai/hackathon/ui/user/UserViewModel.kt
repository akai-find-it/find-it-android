package pl.org.akai.hackathon.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.LoginData
import pl.org.akai.hackathon.data.model.User
import pl.org.akai.hackathon.data.model.UserBase
import pl.org.akai.hackathon.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
	private val apiService: ApiService,
) : BaseViewModel() {

	val user = MutableLiveData<UserBase?>(null)
	val token = MutableLiveData<String?>(null)

	var email = ""
	var password = ""

	fun loadUser() = viewModelScope.launch(Dispatchers.IO) {
		user.postValue(apiService.getMe())
	}

	fun login() {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				val data = apiService.login(LoginData(email, password))
				token.postValue(data.access)
				loadUser()
			} catch (e: Exception) {}
		}
	}
}
