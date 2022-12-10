package com.example.abbtechgraduationproject.data.di

import com.example.abbtechgraduationproject.data.BASE_URL
import com.example.abbtechgraduationproject.data.network.FoodsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideFoodService(factory: GsonConverterFactory): FoodsService {
        val logger = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .client(OkHttpClient.Builder().addInterceptor(logger).build())
            .build()
            .create(FoodsService::class.java)
    }


    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


}