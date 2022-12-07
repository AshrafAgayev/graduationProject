package com.example.abbtechgraduationproject.data.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.abbtechgraduationproject.data.entities.Foods
import com.example.abbtechgraduationproject.data.room.dao.FoodsDao

@Database(entities = [Foods::class], version = 1, )
abstract class FoodsDatabase : RoomDatabase() {

   abstract fun getFoodsDao() :FoodsDao

}