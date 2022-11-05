package pl.org.akai.hackathon.ui.add

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

	override suspend fun loadDataImpl(): List<Pair<Question, AddModel.Answer>> =
		apiService.getQuestionList().map { Pair(it, AddModel.Answer(it.id, "")) }

	fun add(name: String, description: String, category: Int, date: Long) {
		viewModelScope.launch {
			val categories = apiService.getCategoryList()
			val model = AddModel(
				name,
				categories.first { it.id == category },
				description,
				LocalDate.ofEpochDay(date),
				data.value?.map { it.second }?.toMutableList() ?: mutableListOf()
			)
			apiService.addLostItem(model)
		}
	}
}