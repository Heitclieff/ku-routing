package dev.ku.routing.models

data class PaginationModel<T>(
    val status : Int,
    val message : String,
    val page: Int? = null,
    val size: Int? = null,
    val totalElements: Long,
    val content: T
)
