package com.example.abbtechgraduationproject.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.abbtechgraduationproject.data.entities.Foods

@Dao
interface FoodsDao {
    @get:Query("SELECT * FROM foods")
    val allFoods: List<Foods>

    @Insert
    fun insertFood(foods: Foods)



}