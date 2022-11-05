package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json
import java.time.ZonedDateTime

data class User(
	val email: String,
	val name: String,
	@Json(name = "is_staff")
	val isStaff: Boolean,
	@Json(name = "is_superuser")
	val isSuperuser: Boolean,
	@Json(name = "is_active")
	val isActive: Boolean,
	@Json(name = "created_at")
	val createdAt: ZonedDateTime,
)

data class UserBase(
	val id: Int,
	val email: String,
	@Json(name = "first_name")
	val firstName: String,
	@Json(name = "last_name")
	val lastName: String,
)
