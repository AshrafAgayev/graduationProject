package com.example.abbtechgraduationproject.data.entities

import java.io.Serializable

data class Food(
    var id:Int,
    var name:String,
    var image:String,
    var price:Int,
    var category:String
): Serializable {

}