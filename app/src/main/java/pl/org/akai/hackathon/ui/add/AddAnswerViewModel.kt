package pl.org.akai.hackathon.ui.add

import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Question
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class AddAnswerViewModel @Inject constructor(private var apiService: ApiService) : DataViewModel<List<Question>>(
	emptyList()
) {

	override suspend fun loadDataImpl(): List<Question> = listOf(
		Question(1, "Jaki kolor", "np. czerwony", true),
		Question(1, "Jaka wielkosc", "np. duzy", false),
		Question(1, "Jaka tapeta", "np. wkropki", true)
	)
}