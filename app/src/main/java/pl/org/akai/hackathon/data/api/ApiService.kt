package pl.org.akai.hackathon.data.api

import pl.org.akai.hackathon.data.model.LostItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

	@GET("lost-items")
	suspend fun getLostItemList(): ApiList<LostItem>
}
