package pl.org.akai.hackathon.ui.add

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import pl.org.akai.hackathon.ui.base.BaseViewModel

class AddViewModel : BaseViewModel() {

	private var _list: MutableLiveData<Array<String>> = MutableLiveData(
		arrayOf(
			"Elektronika",
			"Ubrania"
		)
	)
	val list: LiveData<Array<String>>
		get() = _list
}