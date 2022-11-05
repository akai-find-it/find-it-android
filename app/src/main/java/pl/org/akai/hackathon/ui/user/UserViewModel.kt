package pl.org.akai.hackathon.ui.user

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.User
import pl.org.akai.hackathon.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
	private val apiService: ApiService,
) : BaseViewModel() {

	val user = MutableLiveData<User?>(null)

	fun loadUser(token: String) = viewModelScope.launch(Dispatchers.IO) {

	}

	fun login(email: String, password: String) = viewModelScope.launch(Dispatchers.IO) {

	}
}
