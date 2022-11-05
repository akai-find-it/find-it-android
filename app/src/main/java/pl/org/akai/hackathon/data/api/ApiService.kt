package pl.org.akai.hackathon.data.api

import pl.org.akai.hackathon.data.model.AddModel
import pl.org.akai.hackathon.data.model.Category
import pl.org.akai.hackathon.data.model.LoginData
import pl.org.akai.hackathon.data.model.LostItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

	@GET("api/v1/lost-items")
	suspend fun getLostItemList(
		@Query("page") page: Int = 1,
		@Query("page_size") pageSize: Int = 10,
	): ApiList<LostItem>

	@GET("api/v1/category")
	suspend fun getCategoryList(): List<Category>

	@POST("api/v1/lost-items/")
	suspend fun addLostItem(
		@Body addModel: AddModel
	)

	@POST("users/jwt/")
	suspend fun login(
		@Body data: LoginData,
	): LoginData.Response
}
