package pl.org.akai.hackathon.ui.base

import androidx.lifecycle.MutableLiveData

abstract class DataViewModel<T>(
	default: T
) : BaseViewModel() {

	val data = MutableLiveData(default)
	val isLoading = MutableLiveData(true)
	val error = MutableLiveData<Throwable>()

	protected abstract suspend fun loadDataImpl(): T

	suspend fun loadData() {
		try {
			data.postValue(loadDataImpl()!!) // it just works
		} catch (e: Exception) {
			error.postValue(e)
		}
		isLoading.postValue(false)
	}
}
