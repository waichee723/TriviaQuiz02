package com.waichee.triviaquiz02.data.entities

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Question(
    @Json(name = "category")
    val category: String?,
    @Json(name = "correct_answer")
    val correct_answer: String?,
    @Json(name = "difficulty")
    val difficulty: String?,
    @Json(name = "incorrect_answers")
    val incorrect_answers: List<String>?,
    @Json(name = "question")
    val question: String?,
    @Json(name = "type")
    val type: String?
)