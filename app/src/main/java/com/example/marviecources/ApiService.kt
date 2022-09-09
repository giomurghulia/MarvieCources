package com.example.marviecources

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("4167a598-b68c-420f-b6e1-fef68b89a10d")
    suspend fun getCourse(): Response<CourseResult>
}