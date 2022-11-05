package pl.org.akai.hackathon.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.AddModel
import pl.org.akai.hackathon.data.model.Question
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class AddGuessViewModel @Inject constructor(private var apiService: ApiService) : DataViewModel<List<Pair<Pair<Int, Question>, AddModel.Answer>>>(
	emptyList()
) {
	var itemId = 0
	private var _navigateBack: MutableLiveData<Boolean?> = MutableLiveData(false)
	val navigateBack: LiveData<Boolean?>
		get() = _navigateBack

	fun endNavigateBack() {
		_navigateBack.value = null
	}

	override suspend fun loadDataImpl(): List<Pair<Pair<Int, Question>, AddModel.Answer>> =
		apiService.getAnsweredList(itemId).map { Pair(it.answerId to it.question, AddModel.Answer(it.question.id, "")) }

	fun add(id: Int) {
		viewModelScope.launch(Dispatchers.IO) {
			apiService.addGuesses(id, data.value?.map { it.second } ?: mutableListOf())
		}
	}
}
