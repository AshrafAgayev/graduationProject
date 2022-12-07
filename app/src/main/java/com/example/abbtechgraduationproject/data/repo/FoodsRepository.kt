package com.example.abbtechgraduationproject.data.repo

import com.example.abbtechgraduationproject.data.entities.CartResponse
import com.example.abbtechgraduationproject.data.entities.Foods
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.network.FoodsService
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject


class FoodsRepository @Inject constructor(private var service: FoodsService) {


    suspend fun getAll(): List<Foods>{

       return service.getAll().foods
    }


    suspend fun getAllFoodsFromCart(): List<FoodsOnCart>{
        return service.getAllFoodsFromCart().foodsOnCart
    }

    suspend fun addToCart(name:String, image: String, price:Int, category:String, orderAmount:Int, username:String){
        return service.addToCart(name, image, price, category, orderAmount, username)
    }

    suspend fun getFromCart(userName:String): List<FoodsOnCart>{
        return service.getFromCart(userName).foodsOnCart
    }


}