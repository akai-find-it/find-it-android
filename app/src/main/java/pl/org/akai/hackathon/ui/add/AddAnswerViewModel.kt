package pl.org.akai.hackathon.ui.add

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import pl.org.akai.hackathon.data.DataAdapters
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.AddModel
import pl.org.akai.hackathon.data.model.Question
import pl.org.akai.hackathon.ext.createPartFromString
import pl.org.akai.hackathon.ui.base.DataViewModel
import java.io.ByteArrayOutputStream
import java.time.LocalDate
import java.util.UUID
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

	fun add(name: String, description: String, category: Int, date: Long, bitmap: Bitmap) {
		viewModelScope.launch {

			val stream = ByteArrayOutputStream()
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
			val byteArray = stream.toByteArray()
			val requestFile: RequestBody =
				byteArray.toRequestBody(
					"multipart/form-data".toMediaTypeOrNull(),
					0,
					byteArray.size
				)
			val photo =
				MultipartBody.Part.createFormData(
					"image_url",
					"${UUID.randomUUID()}.jpg",
					requestFile
				)

			//
			val lostItem = apiService.addLostItem(
				createPartFromString(name),
				category,
				createPartFromString(description),
				createPartFromString(DataAdapters().toJson(LocalDate.ofEpochDay(date))),
				photo
			)
			for (answer in data.value?.map { it.second }?.toMutableList() ?: mutableListOf()) {
				if (answer.value.isBlank())
					continue
				apiService.addAnswer(lostItem.id, answer)
			}
			_navigateBack.postValue(true)
		}
	}
}
