package pl.org.akai.hackathon.data

import androidx.room.Database
import androidx.room.RoomDatabase
import pl.org.akai.hackathon.data.model.ChatMessage

@Database(entities = [ChatMessage::class], version = 1)
abstract class AppDb : RoomDatabase() {
	abstract fun chatDao(): ChatDao
}

