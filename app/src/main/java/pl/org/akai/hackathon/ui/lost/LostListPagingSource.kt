package pl.org.akai.hackathon.ui.lost

import androidx.paging.PagingSource
import androidx.paging.PagingState
import pl.org.akai.hackathon.data.api.ApiService
import pl.org.akai.hackathon.data.model.LostItem

class LostListPagingSource(
	private val apiService: ApiService,
) : PagingSource<Int, LostItem>() {

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, LostItem> {
		return try {
			val (data, paging) = apiService.getLostItemList()
			LoadResult.Page(
				data = data,
				prevKey = if (paging.currentPage == 1) null else paging.currentPage,
				nextKey = if (paging.currentPage == paging.lastPage) null else paging.currentPage + 1,
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, LostItem>): Int? {
		return null
	}
}
