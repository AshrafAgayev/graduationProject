package com.example.abbtechgraduationproject.data.repo

import android.util.Log
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.CartResponse
import com.example.abbtechgraduationproject.data.entities.Food
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.entities.FoodsResponse
import com.example.abbtechgraduationproject.data.network.FoodsService
import com.example.abbtechgraduationproject.utils.or
import com.example.abbtechgraduationproject.utils.toSafeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
