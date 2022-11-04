package pl.org.akai.hackathon.data.api

open class ApiResponse<T> {
	data class Error<T>(val throwable: Throwable) : ApiResponse<T>()
	data class Success<T>(val data: T) : ApiResponse<T>()

	companion object {
		fun <T> error(throwable: Throwable) = Error<T>(throwable)
		fun <T> success(data: T) = Success(data)
	}
}
