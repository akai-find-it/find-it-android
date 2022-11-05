package pl.org.akai.hackathon.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.Category
import pl.org.akai.hackathon.ui.base.DataViewModel
import javax.inject.Inject

@HiltViewModel
class AddViewModel @Inject constructor(private var apiService: ApiService) : DataViewModel<List<Category>>(
	listOf(Category(0, "Ładowanie kategorii..."))
) {

	private var _list: LiveData<Array<String>> = Transformations.map(data) {
		it.map { it.name }.toTypedArray()
	}
	val list: LiveData<Array<String>>
		get() = _list

	override suspend fun loadDataImpl(): List<Category> = apiService.getCategoryList()

}