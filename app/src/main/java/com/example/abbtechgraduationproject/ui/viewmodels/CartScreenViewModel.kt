package com.example.abbtechgraduationproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbtechgraduationproject.data.USERNAME
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartScreenViewModel @Inject constructor(var repo: FoodsRepository) : ViewModel() {

    private val _foodsOnCart = MutableLiveData<List<FoodsOnCart>>()
    val foodsOnCart: LiveData<List<FoodsOnCart>> = _foodsOnCart

    init {
        getFromCart(USERNAME)
    }

    fun getFromCart(userName: String) {
        viewModelScope.launch {
            _foodsOnCart.value = repo.getFromCart(userName)
        }
    }


    fun deleteFromCart(id: Int, userName: String) {
        viewModelScope.launch {
            repo.deleteFromCart(id, userName)
        }
    }

}