package com.example.abbtechgraduationproject.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.abbtechgraduationproject.data.TABLE_NAME_FOODS
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = TABLE_NAME_FOODS)
data class Food(
    @PrimaryKey(autoGenerate = true)
    var id:Int,
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "image")
    var image:String,
    @ColumnInfo(name = "price")
    var price:Int,
    @ColumnInfo(name = "category")
    var category:String
): Serializable {

}