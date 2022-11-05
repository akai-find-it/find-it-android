package pl.org.akai.hackathon.data.model

data class LoginData(
	val email: String,
	val password: String,
) {

	data class Response(
		val access: String,
		val refresh: String,
	)
}
