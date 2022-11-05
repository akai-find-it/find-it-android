package pl.org.akai.hackathon.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import pl.org.akai.hackathon.data.model.ChatMessage

@Dao
interface ChatDao {
	@Query("SELECT * FROM chatmessage GROUP BY userId")
	suspend fun getAll(): List<ChatMessage>

	@Query("SELECT * FROM chatmessage WHERE userId = :userId")
	fun getByPersonId(userId: Int): LiveData<List<ChatMessage>>

	@Insert
	fun insert(message: ChatMessage)
}

