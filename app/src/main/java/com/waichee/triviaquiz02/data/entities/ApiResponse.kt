package com.waichee.triviaquiz02.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    @Json(name = "response_code")
    val response_code: Int?,
    @Json(name = "results")
    val results: List<Question>? = listOf()
)