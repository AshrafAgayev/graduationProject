package com.example.abbtechgraduationproject.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.network.FoodRepo
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import com.example.abbtechgraduationproject.utils.toSafeList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartScreenViewModel @Inject constructor(var repo: FoodsRepository, val mRepo: FoodRepo) :
    BaseViewModel() {

    private val _foodsOnCart = MutableLiveData<List<FoodsOnCart>>()
    val foodsOnCart: LiveData<List<FoodsOnCart>> = _foodsOnCart

    init {
        getFromCart(USERNAME)
    }

    fun getFromCart(userName: String) {
        CoroutineScope(Dispatchers.Main).launch {

            try {
                _foodsOnCart.value = repo.getFromCart(userName)
            }catch (e:Exception){
                _foodsOnCart.value = listOf()
            }
        }
    }

    fun deleteFromCart(id: Int?, userName: String?) =launch{
            repo.deleteFromCart(id, userName)
            getFromCart(USERNAME)
    }
}