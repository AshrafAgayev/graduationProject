package com.example.abbtechgraduationproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartScreenViewModel @Inject constructor(var repo: FoodsRepository) : ViewModel() {

    var foodsOnCart = MutableLiveData<List<FoodsOnCart>>()

    init {
        getFromCart("Ashraf")
    }

    fun getFromCart(userName:String){
        CoroutineScope(Dispatchers.Main).launch{
            foodsOnCart.value = repo.getFromCart(userName)
        }
    }

}