package com.example.abbtechgraduationproject.data.network

import com.example.abbtechgraduationproject.data.entities.CartResponse
import com.example.abbtechgraduationproject.data.entities.Foods
import com.example.abbtechgraduationproject.data.entities.FoodsResponse
import retrofit2.http.*

interface FoodsService {

    //http://kasimadalan.pe.hu/
    @GET("foods/getAllFoods.php")
    suspend fun getAll(): FoodsResponse


    //inser to cart http://kasimadalan.pe.hu/foods/insertFood.php
    @POST("foods/insertFood.php")
    @FormUrlEncoded
    suspend fun addToCart(
        @Field("name") name: String, @Field("image") image: String,
        @Field("price") price:Int, @Field("category") category:String,
        @Field("orderAmount") orderAmount:Int, @Field("userName") username: String)



    //get from cart http://kasimadalan.pe.hu/foods/getFoodsCart.php

    @POST("foods/getFoodsCart.php")
    @FormUrlEncoded
    suspend fun getFromCart(@Field("userName") username: String): CartResponse



    //http://kasimadalan.pe.hu/foods/getAllFoodsCart.php
    @POST("foods/getAllFoodsCart.php")
    suspend fun getAllFoodsFromCart(): CartResponse

    //delete from cart http://kasimadalan.pe.hu/foods/deleteFood.php

    @POST("foods/deleteFood.php")
    suspend fun deleteFromCart(@Field("cartId") id:Int, @Field("userName") username:String)


}