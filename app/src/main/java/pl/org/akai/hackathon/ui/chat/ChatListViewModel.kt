package pl.org.akai.hackathon.ui.chat

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.AppDb
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.ChatMessage
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
	private val appDb: AppDb,
) : DataViewModel<List<ChatMessage>>(emptyList()) {

	override suspend fun loadDataImpl(): List<ChatMessage> {
		return appDb.chatDao().getAll()
	}
}
