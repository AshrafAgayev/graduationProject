package com.example.abbtechgraduationproject.data.network

import com.example.abbtechgraduationproject.data.entities.CartResponse
import kotlinx.coroutines.flow.Flow

interface FoodRepo{
    fun getFromCart(username:String):Flow<CartResponse>
}