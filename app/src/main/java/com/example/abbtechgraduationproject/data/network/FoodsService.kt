package com.example.abbtechgraduationproject.data.network

import com.example.abbtechgraduationproject.data.entities.CartResponse
import com.example.abbtechgraduationproject.data.entities.Foods
import com.example.abbtechgraduationproject.data.entities.FoodsResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface FoodsService {

    //http://kasimadalan.pe.hu/
    @GET("foods/getAllFoods.php")
    suspend fun getAll() : FoodsResponse


    //inser to cart http://kasimadalan.pe.hu/foods/insertFood.php
    @POST("foods/{}")


    //get from cart http://kasimadalan.pe.hu/foods/getFoodsCart.php

    @GET("foods/getFoodsCart.php")
    suspend fun getFromCart(): List<Foods>
    //insert to cart http://kasimadalan.pe.hu/foods/insertFood.php



    //http://kasimadalan.pe.hu/foods/getAllFoodsCart.php
    @GET("foods/getAllFoodsCart.php")
    suspend fun getAllFoodsFromCart(): CartResponse

    //delete from cart http://kasimadalan.pe.hu/foods/deleteFood.php


    //get image http://kasimadalan.pe.hu/foods/images/meatball.png

    @GET("foods/images/{imageName}")
    suspend fun getImage(@Path("imageName") name:String) : String
}