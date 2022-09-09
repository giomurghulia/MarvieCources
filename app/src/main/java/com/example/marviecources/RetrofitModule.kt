package com.example.marviecources

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(retrofitClient: RetrofitClient): Retrofit {
        return retrofitClient.retrofit
    }

}

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideCourseApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}