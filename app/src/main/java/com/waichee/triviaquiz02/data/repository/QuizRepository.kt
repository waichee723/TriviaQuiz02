package com.waichee.triviaquiz02.data.repository

import androidx.lifecycle.LiveData
import com.waichee.triviaquiz02.data.entities.ApiResponse
import com.waichee.triviaquiz02.data.remote.QuizRemoteDataSource
import com.waichee.triviaquiz02.utils.Resource
import com.waichee.triviaquiz02.utils.performGetOperation
import timber.log.Timber
import javax.inject.Inject

class QuizRepository @Inject constructor(private val remoteDataSource: QuizRemoteDataSource) {
    fun getQuestions(amount: Int): LiveData<Resource<ApiResponse>> {
        val response = performGetOperation {
            remoteDataSource.getQuestions(amount)
        }
        Timber.i(response.value.toString())
        return response
    }
}