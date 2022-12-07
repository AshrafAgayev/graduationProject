package com.example.abbtechgraduationproject.data.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FoodsResponse(
   var foods: List<Foods>
) :Serializable