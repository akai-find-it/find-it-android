package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json
import java.time.LocalDate
import java.time.ZonedDateTime

data class LostItem(
	val id: Int,
	val title: String,
	val description: String,
	@Json(name = "found_at")
	val foundAt: LocalDate,
	val category: Category,
	val founder: UserBase,
	@Json(name = "created_at")
	val createdAt: ZonedDateTime,
)

data class LostItemBase(
	val id: Int,
	val title: String,
	val description: String,
)
