package com.waichee.triviaquiz02.data

import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {
    @GET("api.php")
    suspend fun getQuestions(@Query("amount") amount: Int)
}