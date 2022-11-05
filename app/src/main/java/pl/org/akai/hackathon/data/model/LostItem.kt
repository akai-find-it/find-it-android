package pl.org.akai.hackathon.data.model

import java.time.ZonedDateTime
import java.util.*

data class LostItem(
	val id: Int,
	val title: String,
	val description: String,
	val foundAt: Date,
	val category: String,
	val founder: User,
	val createdAt: ZonedDateTime,
)
