package com.example.abbtechgraduationproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.CartResponse
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import com.example.abbtechgraduationproject.utils.toSafeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(var repo: FoodsRepository) : ViewModel() {

    private val _foodsOnCart = MutableLiveData<List<FoodsOnCart>>()
    val foodsOnCart: LiveData<List<FoodsOnCart>> = _foodsOnCart

    fun addToCart(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        username: String
    ) {
        CoroutineScope(Dispatchers.Main).launch {
            repo.addToCart(name, image, price, category, orderAmount, username)
        }
    }


    fun getFromCart(userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                _foodsOnCart.value = repo.getFromCart(userName)
            } catch (e: Exception) {
                _foodsOnCart.value = listOf()
            }
        }
    }


    fun deleteFromCart(id: Int, userName: String) {
        CoroutineScope(Dispatchers.Main).launch {
            repo.deleteFromCart(id, userName)
            getFromCart(USERNAME)
        }
    }
}