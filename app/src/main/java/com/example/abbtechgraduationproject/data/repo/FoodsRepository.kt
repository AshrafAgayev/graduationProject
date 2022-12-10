package com.example.abbtechgraduationproject.data.repo

import com.example.abbtechgraduationproject.data.entities.Food
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.network.FoodsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class FoodsRepository @Inject constructor(private var service: FoodsService) {


    suspend fun getAll(): List<Food> {
        return service.getAll().foods
    }


    suspend fun addToCart(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        username: String
    ) {
        return service.addToCart(name, image, price, category, orderAmount, username)
    }

    suspend fun getFromCart(userName: String): List<FoodsOnCart> =
        withContext(Dispatchers.IO) {
            val response = service.getFromCart(userName)
            return@withContext response.foodsOnCart
        }

    suspend fun deleteFromCart(id: Int?, username: String?) {
        return service.deleteFromCart(id, username)
    }
}
