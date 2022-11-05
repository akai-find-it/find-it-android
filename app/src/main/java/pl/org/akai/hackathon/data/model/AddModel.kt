package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json
import java.time.LocalDate

data class AddModel(
	var title: String = "",
	var category: Int = 0,
	var description: String = "",

	@Json(name = "found_at")
	var foundAt: LocalDate = LocalDate.now(),

	val answers: MutableList<Answer> = mutableListOf(),
) {
	data class Answer(
		val question: Int,
		var value: String,
	)
	data class Guess(
		val answer: Int,
		var value: String,
	)
}
