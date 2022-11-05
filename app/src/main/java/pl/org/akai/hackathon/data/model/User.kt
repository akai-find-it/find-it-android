package pl.org.akai.hackathon.data.model

import java.time.ZonedDateTime

data class User(
	val email: String,
	val isStaff: Boolean,
	val isSuperuser: Boolean,
	val isActive: Boolean,
	val createdAt: ZonedDateTime,
)
