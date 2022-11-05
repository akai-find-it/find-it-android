package pl.org.akai.hackathon.ui.base

import androidx.lifecycle.MutableLiveData

abstract class DataViewModel<T>(
	default: T,
) : BaseViewModel() {

	val data = MutableLiveData(default)
	val isLoading = MutableLiveData(true)
	val error = MutableLiveData<Throwable>(null)

	protected abstract suspend fun loadDataImpl(): T

	suspend fun loadData() {
		isLoading.postValue(true)
		try {
			data.postValue(loadDataImpl()!!) // it just works
		} catch (e: Exception) {
			error.postValue(e)
		}
		isLoading.postValue(false)
	}
}
