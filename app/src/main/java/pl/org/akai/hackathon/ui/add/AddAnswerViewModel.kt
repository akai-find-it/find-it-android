package pl.org.akai.hackathon.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.AddModel
import pl.org.akai.hackathon.data.model.Question
import pl.org.akai.hackathon.ui.base.DataViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class AddAnswerViewModel @Inject constructor(private var apiService: ApiService) : DataViewModel<List<Pair<Question, AddModel.Answer>>>(
	emptyList()
) {
	var categoryId = 0
	private var _navigateBack: MutableLiveData<Boolean?> = MutableLiveData(false)
	val navigateBack: LiveData<Boolean?>
		get() = _navigateBack

	fun endNavigateBack() {
		_navigateBack.value = null
	}

	override suspend fun loadDataImpl(): List<Pair<Question, AddModel.Answer>> =
		apiService.getQuestionList(categoryId).map { Pair(it, AddModel.Answer(it.id, "")) }

	fun add(name: String, description: String, category: Int, date: Long) {
		viewModelScope.launch {
			val model = AddModel(
				name,
				category,
				description,
				LocalDate.ofEpochDay(date),
				data.value?.map { it.second }?.toMutableList() ?: mutableListOf()
			)
			val lostItem = apiService.addLostItem(model)
			for (answer in model.answers) {
				if (answer.value.isBlank())
					continue
				apiService.addAnswer(lostItem.id, answer)
			}
			_navigateBack.value = true
		}
	}
}
