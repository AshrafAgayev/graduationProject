package com.example.abbtechgraduationproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(var repo: FoodsRepository) : BaseViewModel() {

    private val _foodsOnCart = MutableLiveData<List<FoodsOnCart>>()
    val foodsOnCart: LiveData<List<FoodsOnCart>> = _foodsOnCart

    fun addToCart(
        name: String,
        image: String,
        price: Int,
        category: String,
        orderAmount: Int,
        username: String
    ) = launch {
        repo.addToCart(name, image, price, category, orderAmount, username)
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


    fun deleteFromCart(id: Int, userName: String) = launch{
            repo.deleteFromCart(id, userName)
            getFromCart(USERNAME)
    }
}