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
			val page = params.key ?: 1
			val perPage = params.loadSize
			val data = apiService.getLostItemList(page, perPage)
			LoadResult.Page(
				data = data.results,
				prevKey = if (data.prevPage == null) null else page,
				nextKey = if (data.nextPage == null) null else page + 1,
			)
		} catch (e: Exception) {
			LoadResult.Error(e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, LostItem>): Int? {
		return null
	}
}
