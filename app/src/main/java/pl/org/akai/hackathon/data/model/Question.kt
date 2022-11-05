package pl.org.akai.hackathon.data.model

data class Question(
	val id: Int,
	val title: String,
	val hint: String,
	val category: Category,
	val required: Boolean,
)
