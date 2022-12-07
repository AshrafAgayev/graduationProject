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

    suspend fun getImage(imageName:String) : String{
        return service.getImage(imageName)
    }

    suspend fun getAllFoodsFromCart(): List<FoodsOnCart>{
        return service.getAllFoodsFromCart().foodsOnCart
    }


}