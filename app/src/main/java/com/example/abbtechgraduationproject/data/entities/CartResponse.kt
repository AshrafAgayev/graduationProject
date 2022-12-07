package com.example.abbtechgraduationproject.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CartResponse(
    @SerializedName("foods_cart")
    var foodsOnCart : List<FoodsOnCart>

) :Serializable{
}