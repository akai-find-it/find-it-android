package pl.org.akai.hackathon.data.api

open class ApiList<T>(
	val data: List<T>,
	val paging: Paging,
) {
	data class Paging(
		val currentPage: Int,
		val lastPage: Int,
		val perPage: Int,
		val totalCount: Int,
	)
}

