package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json
import java.time.ZonedDateTime

data class Answer(
	val id: Int,
	val item: LostItem,
	val value: String,
	@Json(name = "created_at")
	val createdAt: ZonedDateTime,
)
