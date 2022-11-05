package pl.org.akai.hackathon.ui.lost

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.LostItem
import pl.org.akai.hackathon.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class LostListViewModel @Inject constructor(
	private val apiService: ApiService,
) : BaseViewModel() {

	private var pagingSource: LostListPagingSource? = null
	val flow = Pager(
		PagingConfig(pageSize = 10, initialLoadSize = 20),
	) {
		pagingSource = LostListPagingSource(apiService)
		pagingSource!!
	}.flow.cachedIn(viewModelScope)

	fun onRefresh() {
		pagingSource?.invalidate()
	}

	fun onItemClicked(item: LostItem) {
		navigate(LostListFragmentDirections.actionLostListFragmentToItemFragment(item.id))
	}
}
