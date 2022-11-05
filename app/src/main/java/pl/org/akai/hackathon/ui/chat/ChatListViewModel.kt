package pl.org.akai.hackathon.ui.chat

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.ChatMessage
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
	private val apiService: ApiService,
) : DataViewModel<List<ChatMessage>>(emptyList()) {

	var id: Int = 0

	override suspend fun loadDataImpl(): List<ChatMessage> {
		return emptyList()
//		return apiService.getLostItem(id)
	}

}