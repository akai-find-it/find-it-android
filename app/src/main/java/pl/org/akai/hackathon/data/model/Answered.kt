package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json

data class Answered(
	@Json(name = "id")
	val answerId: Int,
	val question: Question,
)
