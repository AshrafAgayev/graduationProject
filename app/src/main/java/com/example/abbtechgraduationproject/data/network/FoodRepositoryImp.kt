package com.example.abbtechgraduationproject.data.network

import com.example.abbtechgraduationproject.data.entities.CartResponse
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FoodRepositoryImp @Inject constructor(
    private val service: FoodsService
) :BaseRepository(), FoodRepo{
    override fun getFromCart(username: String): Flow<CartResponse> =
        sendRequest { service.getAllFoodsFromCart() }
}