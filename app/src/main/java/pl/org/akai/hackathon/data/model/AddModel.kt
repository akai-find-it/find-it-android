package pl.org.akai.hackathon.data.model

import java.util.Date

data class AddModel(
	var name: String = "",
	var category: Category? = null,
	var description: String = "",
	var foundAt: Date,
)