package pl.org.akai.hackathon.data.model

import java.time.LocalDate
import java.time.ZonedDateTime

data class LostItem(
	val id: Int,
	val title: String,
	val description: String,
	val foundAt: LocalDate,
	val category: Category,
	val founder: User,
	val createdAt: ZonedDateTime,
)
