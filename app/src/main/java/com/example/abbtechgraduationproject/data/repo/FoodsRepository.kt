package com.example.abbtechgraduationproject.data.repo

import android.util.Log
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.CartResponse
import com.example.abbtechgraduationproject.data.entities.Food
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.entities.FoodsResponse
import com.example.abbtechgraduationproject.data.network.FoodsService
import com.example.abbtechgraduationproject.utils.or
import com.example.abbtechgraduationproject.utils.toSafeList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class FoodsRepository @Inject constructor(private var service: FoodsService) {


    suspend fun getAll(): List<Food> {
        return service.getAll().foods
    }


    suspend fun getAllFoodsFromCart(): List<FoodsOnCart> {
        return service.getAllFoodsFromCart().foodsOnCart ?: emptyList()
    }

    suspend fun addToCart(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        username: String
    ) {
        return service.addToCart(name, image, price, category, orderAmount, username)
    }

    suspend fun getFromCart(userName: String): List<FoodsOnCart> =
        withContext(Dispatchers.IO) {
            val response = service.getFromCart(userName)
            return@withContext response.foodsOnCart
        }


//        if (response.isSuccessful && response.body() != null && response.body().toString() != "") {
//            Log.e("RESPONSE", "${response.body()}")
//
//            return response.body()!!.foodsOnCart.toSafeList()
//
//        } else if (response.errorBody() != null) {
//            Log.e("RESPONSE", "${response.errorBody()}")
//            return emptyList()
//        } else {
//            Log.e("RESPONSE", "${response.body()}")
//            return emptyList()
//        }


//        var dataCall : Call<CartResponse>? = service.getFromCart(userName)
//
//        var list: List<FoodsOnCart>?
//
//        dataCall?.enqueue( object: Callback<CartResponse?> {
//            override fun onResponse(call: Call<CartResponse?>, response: Response<CartResponse?>) : List<FoodsOnCart> {
//                list = response.body()?.foodsOnCart
//                return list.toSafeList()
//            }
//
//            override fun onFailure(call: Call<CartResponse?>, t: Throwable) : List<FoodsOnCart> {
//                list = emptyList()
//                return list.toSafeList()
//            }
//
//
//        })
//




    suspend fun deleteFromCart(id: Int?, username: String?) {
        return service.deleteFromCart(id, username)
    }
}
