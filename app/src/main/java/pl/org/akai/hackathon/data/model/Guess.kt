package pl.org.akai.hackathon.data.model

import java.time.ZonedDateTime

data class Guess(
	val answer: Answer,
	val value: String,
	val user: User,
	val createdAt: ZonedDateTime,
)
