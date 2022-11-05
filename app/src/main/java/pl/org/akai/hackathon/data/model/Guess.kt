package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json
import java.time.ZonedDateTime

data class Guess(
	val answer: Answer,
	val value: String,
	val user: User,
	@Json(name = "created_at")
	val createdAt: ZonedDateTime,
)
