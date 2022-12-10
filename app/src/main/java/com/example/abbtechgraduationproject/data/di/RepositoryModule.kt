package com.example.abbtechgraduationproject.data.di

import com.example.abbtechgraduationproject.data.network.FoodRepo
import com.example.abbtechgraduationproject.data.network.FoodRepositoryImp
import com.example.abbtechgraduationproject.data.network.FoodsService
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(service: FoodsService): FoodsRepository{
        return FoodsRepository(service)
    }

    @Provides
    @Singleton
    fun provideImpl(service: FoodsService):FoodRepo= FoodRepositoryImp(service)

}