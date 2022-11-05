package pl.org.akai.hackathon.data.api

import pl.org.akai.hackathon.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

	@GET("api/v1/lost-items")
	suspend fun getLostItemList(
		@Query("page")
		page: Int = 1,
		@Query("page_size")
		pageSize: Int = 10,
	): ApiList<LostItem>

	@GET("api/v1/categories")
	suspend fun getCategoryList(): List<Category>

	@GET("api/v1/categories/{id}/questions")
	suspend fun getQuestionList(
		@Path("id")
		id: Int,
	): List<Question>

	@POST("api/v1/lost-items/new")
	suspend fun addLostItem(
		@Body
		addModel: AddModel,
	)

	@GET("api/v1/lost-items/{id}")
	suspend fun getLostItem(
		@Path("id")
		id: Int,
	): LostItem

	@POST("users/jwt/")
	suspend fun login(
		@Body
		data: LoginData,
	): LoginData.Response

	@POST("api/v1/notification/")
	suspend fun sendPushToken(
		@Body token: TokenRequest,
	)
}
