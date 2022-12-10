package com.example.abbtechgraduationproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.abbtechgraduationproject.data.TABLE_NAME_FOODS
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Food(
    var id:Int,
    var name:String,
    var image:String,
    var price:Int,
    var category:String
): Serializable {

}