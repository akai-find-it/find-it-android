package pl.org.akai.hackathon.data.model

import com.squareup.moshi.Json
import java.util.Date

data class AddModel(
	var name: String = "",
	var category: Category? = null,
	var description: String = "",

	@Json(name = "found_at")
	var foundAt: Date,
)