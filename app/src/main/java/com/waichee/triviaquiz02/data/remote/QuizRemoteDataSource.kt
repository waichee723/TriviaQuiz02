package com.waichee.triviaquiz02.data.remote

import javax.inject.Inject

class QuizRemoteDataSource @Inject constructor(private val quizService: QuizService) : BaseDataSource() {
    suspend fun getQuestions(amount: Int) = getResult { quizService.getQuestions(amount) }
}