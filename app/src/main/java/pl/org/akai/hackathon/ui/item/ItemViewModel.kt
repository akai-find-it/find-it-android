package pl.org.akai.hackathon.ui.item

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.LostItem
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
	private val apiService: ApiService,
) : DataViewModel<LostItem?>(null) {

	var id: Int = 0

	override suspend fun loadDataImpl(): LostItem {
		return apiService.getLostItem(id)
	}

	fun onRefresh() = viewModelScope.launch(Dispatchers.IO) {
		loadData()
	}
}
