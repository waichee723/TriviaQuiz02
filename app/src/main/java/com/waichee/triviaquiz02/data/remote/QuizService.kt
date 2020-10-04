package com.waichee.triviaquiz02.data.remote

import com.waichee.triviaquiz02.data.entities.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuizService {
    @GET("api.php")
    suspend fun getQuestions(@Query("amount") amount: Int): Response<ApiResponse>
}