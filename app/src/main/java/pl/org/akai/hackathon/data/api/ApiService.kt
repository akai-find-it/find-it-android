package pl.org.akai.hackathon.data.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import pl.org.akai.hackathon.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

	@Multipart
	@POST("api/v1/lost-items/new")
	suspend fun addLostItem(
		@Part("title")
		title: RequestBody,
		@Part("category")
		category: Int,
		@Part("description")
		description: RequestBody,
		@Part("found_at")
		found_at: RequestBody,
		@Part
		image_url: MultipartBody.Part,
	): LostItemBase

	@POST("api/v1/lost-items/{id}/answers/new")
	suspend fun addAnswer(
		@Path("id") itemId: Int,
		@Body answer: AddModel.Answer,
	)

	@POST("api/v1/lost-items/{id}/guesses/many")
	suspend fun addGuesses(
		@Path("id") itemId: Int,
		@Body answers: List<AddModel.Guess>,
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

	@POST("api/v1/chat/{id}")
	suspend fun chatSend(
		@Path("id") userId: Int,
		@Body message: ChatMessage,
	)
}
