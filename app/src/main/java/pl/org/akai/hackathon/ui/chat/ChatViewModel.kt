package pl.org.akai.hackathon.ui.chat

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.AppDb
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.ChatMessage
import pl.org.akai.hackathon.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
	private val apiService: ApiService,
	val appDb: AppDb,
) : BaseViewModel() {

	suspend fun sendMessage(userId: Int, message: ChatMessage) {
		apiService.chatSend(userId, message)
		appDb.chatDao().insert(message)
	}
}
