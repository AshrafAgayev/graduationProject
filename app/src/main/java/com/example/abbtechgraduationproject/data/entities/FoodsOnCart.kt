package com.example.abbtechgraduationproject.data.entities

import java.io.Serializable


    data class FoodsOnCart(
        var cartId:Int,
        var name:String,
        var image:String,
        var price:Int,
        var category:String,
        var orderAmount:Int,
        var userName:String
    ): Serializable {

    }