package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json
import java.time.LocalDate

data class AddModel(
	var name: String = "",
	var category: Category? = null,
	var description: String = "",

	@Json(name = "found_at")
	var foundAt: LocalDate? = null,

	val answers: MutableList<Answer> = mutableListOf(),
) {
	data class Answer(
		val question: Int,
		var answer: String,
	)
}