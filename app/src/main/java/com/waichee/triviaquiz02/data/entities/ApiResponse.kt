package com.waichee.triviaquiz02.data.entities

data class ApiResponse(
    val response_code: Int?,
    val results: List<Question>?
)