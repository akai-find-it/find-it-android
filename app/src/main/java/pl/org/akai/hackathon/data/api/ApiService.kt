package pl.org.akai.hackathon.data.api

import pl.org.akai.hackathon.data.model.LostItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

	@GET("lost-items")
	suspend fun getLostItemList(
		@Query("page") page: Int = 1,
		@Query("page_size") pageSize: Int = 10,
	): ApiList<LostItem>
}
