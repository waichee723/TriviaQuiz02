package com.waichee.triviaquiz02.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.waichee.triviaquiz02.data.remote.QuizRemoteDataSource
import com.waichee.triviaquiz02.data.remote.QuizService
import com.waichee.triviaquiz02.data.repository.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://opentdb.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Provides
    fun provideQuizService(retrofit: Retrofit): QuizService = retrofit.create(QuizService::class.java)


    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: QuizRemoteDataSource) = QuizRepository(remoteDataSource)
}