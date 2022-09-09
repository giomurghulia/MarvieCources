package com.example.marviecources

import java.lang.IllegalStateException
import javax.inject.Inject


class CourseRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun getCourse(): CourseResult {
        val response = apiService.getCourse()

        if (response.isSuccessful) {
            return response.body()!!
        }

        throw IllegalStateException()

    }
}