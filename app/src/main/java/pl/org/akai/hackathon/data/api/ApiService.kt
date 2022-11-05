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

	@GET("api/v1/lost-items/{id}/answered")
	suspend fun getAnsweredList(
		@Path("id")
		itemId: Int,
	): List<Answered>

	@POST("api/v1/lost-items/new")
	suspend fun addLostItem(
		@Body
		addModel: AddModel,
	)

	@POST("api/v1/lost-items/{id}/answers")
	suspend fun addAnswers(
		@Path("id") itemId: Int,
		@Body answers: List<AddModel.Answer>,
	)

	@POST("api/v1/lost-items/{id}/guesses")
	suspend fun addGuesses(
		@Path("id") itemId: Int,
		@Body answers: List<AddModel.Answer>,
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

	@GET("users/me/")
	suspend fun getMe(): UserBase

	@POST("api/v1/notification/token/")
	suspend fun sendPushToken(
		@Body token: TokenRequest,
	)
}
