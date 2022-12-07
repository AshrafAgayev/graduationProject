package com.example.abbtechgraduationproject.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.abbtechgraduationproject.data.entities.Foods
import com.example.abbtechgraduationproject.data.entities.FoodsOnCart
import com.example.abbtechgraduationproject.data.entities.FoodsResponse
import com.example.abbtechgraduationproject.data.repo.FoodsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(var repo:FoodsRepository) : ViewModel(){

     var foodsList =MutableLiveData<List<Foods>>()


    init {
        getAll()
    }

    fun getAll(){
        CoroutineScope(Dispatchers.Main).launch{
            foodsList.value = repo.getAll()
        }
    }


//    fun getAllFoodsFromCart(){
//        CoroutineScope(Dispatchers.Main).launch{
//            repo.getAllFoodsFromCart()
//        }
//    }

    fun addToCart(name:String, image: String, price:Int, category:String, orderAmount:Int, username:String){
        CoroutineScope(Dispatchers.Main).launch {
            repo.addToCart(name, image, price, category, orderAmount, username)
        }
    }




}