package com.example.abbtechgraduationproject.utils

import android.graphics.drawable.Drawable
import com.example.abbtechgraduationproject.R

var categotyList = listOf(Category(R.drawable.food, "All"), Category(R.drawable.meal, "Meals"), Category(R.drawable.cake, "Desserts"), Category(R.drawable.soda, "Drinks") )

data class Category(var icon: Int, var name:String)