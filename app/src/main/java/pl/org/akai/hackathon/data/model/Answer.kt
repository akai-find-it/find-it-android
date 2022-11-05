package pl.org.akai.hackathon.data.model

import java.time.ZonedDateTime

data class Answer(
	val id: Int,
	val item: LostItem,
	val value: String,
	val createdAt: ZonedDateTime,
)
