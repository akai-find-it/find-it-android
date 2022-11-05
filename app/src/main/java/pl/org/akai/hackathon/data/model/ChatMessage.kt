package pl.org.akai.hackathon.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ChatMessage(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val userId: Int,
	val userName: String,
	val isSent: Boolean,
	val text: String,
	val date: Long,
)

