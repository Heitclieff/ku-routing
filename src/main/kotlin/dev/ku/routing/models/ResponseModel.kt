package dev.ku.routing.models

data class ResponseModel<T>(
    val status : Int,
    val message : String,
    val totalElements : Long,
    val content : T? = null
)
