package com.example.abbtechgraduationproject.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.abbtechgraduationproject.data.entities.Food
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(var repo:FoodsRepository) : ViewModel(){

     private val _foodList =   MutableLiveData<List<Food>>()
    val foodList:LiveData<List<Food>> =
        _foodList

    init {
        getAll()
    }

    fun getAll(){
        viewModelScope.launch{
            _foodList.value = repo.getAll()
        }
    }


    fun addToCart(name:String, image: String, price:Int, category:String, orderAmount:Int, username:String){
        viewModelScope.launch {
            repo.addToCart(name, image, price, category, orderAmount, username)
        }
    }




}