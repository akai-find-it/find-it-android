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
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
	private val apiService: ApiService,
) : DataViewModel<UserBase?>(null) {

	val token = MutableLiveData<String?>(null)

	var email = ""
	var password = ""

	override suspend fun loadDataImpl(): UserBase {
		return apiService.getMe()
	}

	fun login() {
		viewModelScope.launch(Dispatchers.IO) {
			try {
				val data = apiService.login(LoginData(email, password))
				token.postValue(data.access)
				loadData()
			} catch (e: Exception) {}
		}
	}

	fun onMessagesClick() {
		navigate(UserFragmentDirections.actionUserFragmentToChatListFragment())
	}
}
