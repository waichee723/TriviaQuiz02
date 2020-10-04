package com.waichee.triviaquiz02.data.entities

data class Question(
    val category: String?,
    val correct_answer: String?,
    val difficulty: String?,
    val incorrect_answers: List<String>?,
    val question: String?,
    val type: String?
)